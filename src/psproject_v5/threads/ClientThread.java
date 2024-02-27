/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.threads;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import psproject_v5.communication.Operation;
import psproject_v5.communication.Sender;
import psproject_v5.communication.Receiver;
import psproject_v5.communication.Request;
import psproject_v5.communication.Response;
import psproject_v5.controller.Controller;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.EducationTitle;
import psproject_v5.domain.Employee;
import psproject_v5.domain.EmployeeAcademicTitle;
import psproject_v5.domain.IEntity;

/**
 *
 * @author aleks
 */
public class ClientThread extends Thread {

    private Socket socket;
    private ServerThread serverThread;
    private Sender sender;
    private Receiver receiver;

    public ClientThread(Socket socket, ServerThread serverThread) throws IOException {
        super();
        this.socket = socket;
        this.serverThread = serverThread;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        try {
            while (socket != null && !socket.isClosed()) {
                handleClientRequest();
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopClient() {
        try {
            Response response = new Response();
            response.setOperation(Operation.TERMINATE);
            sender.send(response);

            this.sender.close();
            this.receiver.close();
//            this.socket.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleClientRequest() throws Exception {
        try {
            Request request = (Request) receiver.receive();
            System.out.print("primljen zahtev: " + request);

            Operation operation = request.getOperation();
            Response response = null;
            switch (operation) {

                case GET_ALL_ACADEMIC_TITLES -> {
                    try {
                        List<IEntity> entitys = Controller.getInstance().getAll(new AcademicTitle());
                        List<AcademicTitle> list = new ArrayList<>();
                        for (IEntity entity : entitys) {
                            if (entity instanceof AcademicTitle e) {
                                list.add(e);
                            } else {
                                throw new Exception("greska pri punjenju liste");
                            }
                        }
                        response = new Response(list);
                        response.setOperation(operation);
                    } catch (Exception e) {
                        response = new Response(e);
                        response.setOperation(operation);
                    } finally {
                        sender.send(response);
                    }
                }
                case GET_ALL_EDUCATION_TITLES -> {
                    try {
                        List<IEntity> entitys = Controller.getInstance().getAll(new EducationTitle());
                        List<EducationTitle> list = new ArrayList<>();
                        for (IEntity entity : entitys) {
                            if (entity instanceof EducationTitle e) {
                                list.add(e);
                            } else {
                                throw new Exception("greska pri punjenju liste");
                            }
                        }
                        response = new Response(list);
                        response.setOperation(operation);
                    } catch (Exception e) {
                        response = new Response(e);
                        response.setOperation(operation);
                    } finally {
                        sender.send(response);
                    }
                }
                case GET_ALL_EMPLOYEES -> {
                    try {
                        List<IEntity> entitys = Controller.getInstance().getAll(new Employee());
                        List<Employee> list = new ArrayList<>();
                        for (IEntity entity : entitys) {
                            if (entity instanceof Employee e) {
                                list.add(e);
                            } else {
                                throw new Exception("greska pri punjenju liste");
                            }
                        }
                        response = new Response(list);
                        response.setOperation(operation);
                    } catch (Exception e) {
                        response = new Response(e);
                        response.setOperation(operation);
                    } finally {
                        sender.send(response);
                    }
                }
                case GET_ALL_DEPARTMENTS -> {
                    try {
                        List<IEntity> entitys = Controller.getInstance().getAll(new Department());
                        List<Department> list = new ArrayList<>();
                        for (IEntity entity : entitys) {
                            if (entity instanceof Department e) {
                                list.add(e);
                            } else {
                                throw new Exception("greska pri punjenju liste");
                            }
                        }
                        response = new Response(list);
                        response.setOperation(operation);
                    } catch (Exception e) {
                        response = new Response(e);
                        response.setOperation(operation);
                    } finally {
                        sender.send(response);
                    }
                }
                case GET_ALL_EMPLOYEES_FILTERED -> {
                    try {
                        Department department = (Department) request.getArgument();
                        List<IEntity> entitys = Controller.getInstance().getAllFilter(new Employee(), department);
                        List<Employee> list = new ArrayList<>();
                        for (IEntity entity : entitys) {
                            if (entity instanceof Employee e) {
                                list.add(e);
                            } else {
                                throw new Exception("greska pri punjenju liste");
                            }
                        }
                        response = new Response(list);
                        response.setOperation(operation);
                    } catch (Exception e) {
                        response = new Response(e);
                    } finally {
                        sender.send(response);
                    }
                }
                case GET_EMPLOYEE_TITLE_HISTORY -> {
                    try {
                        Employee employee = (Employee) request.getArgument();
                        List<IEntity> entitys = Controller.getInstance().getAllFilter(new EmployeeAcademicTitle(), employee);
                        List<EmployeeAcademicTitle> list = new ArrayList<>();
                        for (IEntity entity : entitys) {
                            if (entity instanceof EmployeeAcademicTitle e) {
                                list.add(e);
                            } else {
                                throw new Exception("greska pri punjenju liste");
                            }
                        }
                        response = new Response(list);
                        response.setOperation(operation);
                    } catch (Exception e) {
                        response = new Response(e);
                        response.setOperation(operation);
                    } finally {
                        sender.send(response);
                    }
                }
                case ADD_ENTITY -> {
                    try {
                        //da svi klijenti odmah vide
                        IEntity entity = (IEntity) request.getArgument();
                        Controller.getInstance().add(entity);
                        response = new Response();
                        response.setOperation(operation);
                        response.setResult(entity);
                        // saljem zbog id!
                        sender.send(response);
                        //OBAVESTI SVE!!!
                        response.setOperation(Operation.NEW_ENTITY_NOTIFICATION);
                        notifyClients(response);
                    } catch (Exception e) {
                        response = new Response(e);
                        sender.send(response);
                    }
                }
                case DELETE_ENTITY -> {
                    try {
                        //da svi klijenti odmah vide
                        IEntity entity = (IEntity) request.getArgument();
                        Controller.getInstance().delete(entity);
                        response = new Response();
                        response.setOperation(operation);
                        response.setResult(entity);
                        //OBAVESTI SVE!!!
//                    response.setOperation(Operation.UPDATE_VIEW);
//                    notifyClients(response);
                    } catch (Exception e) {
                        response = new Response(e);
                        sender.send(response);
                    }
                }
                case UPDATE_ENTITY -> {
                    try {
                        //da svi klijenti odmah vide
                        IEntity entity = (IEntity) request.getArgument();
                        Controller.getInstance().update(entity);
                        response = new Response();
                        response.setOperation(operation);
                        response.setResult(entity);
                        //OBAVESTI SVE!!!
                        response.setOperation(Operation.UPDATE_ENTITY_NOTIFICATION);
                        notifyClients(response);
                    } catch (Exception e) {
                        response = new Response(e);
                        response.setOperation(Operation.UPDATE_ENTITY);
                        sender.send(response);
                    }
                }
                case SAVE_OR_UPDATE_ELEMENTS -> {
                    try {
                        List<IEntity> entitys = (List<IEntity>) request.getArgument();
                        Controller.getInstance().saveOrUpdateList(entitys);

                        response = new Response(entitys);
                        response.setOperation(operation);
                        //OBAVESTI SVE!!!
//                    response.setOperation(Operation.UPDATE_VIEW);
//                    notifyClients(response);
                    } catch (Exception e) {
                        response = new Response(e);
                        sender.send(response);
                    }
                }
                default ->
                    throw new AssertionError();
            }
        } catch (SocketException ex) {
            this.sender.close();
            this.receiver.close();
            this.socket.close();
            System.out.println("dovidjenja!");
            serverThread.removeClient(this);

        } catch (Exception ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void notifyClients(Object object) {

        for (ClientThread clientThread : serverThread.getClientThreads()) {
            if (clientThread.equals(this)) {
                continue;
            } else {
                try {
                    clientThread.sender.send(object);
                } catch (IOException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
