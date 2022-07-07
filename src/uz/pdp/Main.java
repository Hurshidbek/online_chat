package uz.pdp;

import org.w3c.dom.ls.LSOutput;
import uz.pdp.model.Group;
import uz.pdp.model.Message;
import uz.pdp.model.User;
import uz.pdp.service.GroupService;
import uz.pdp.service.MessageService;
import uz.pdp.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        UserService userService = new UserService();
        MessageService messageService = new MessageService();
        GroupService groupService = new GroupService();
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1. Log in\t2. Register\t0. Exit");
            stepCode = scannerInt.nextInt();
            if (stepCode != 0) {
                System.out.println("Enter phone number: ");
                String phoneNumber = scannerStr.nextLine();
                boolean isExist = userService.isExistUser(phoneNumber);
                switch (stepCode) {
                    case 1 -> {
                        if (isExist) {
                            System.out.println("Enter password: ");
                            String password = scannerStr.nextLine();
                            User user = userService.login(phoneNumber, password);
                            if (user != null) {
                                if (!user.isActive()) {
                                    System.out.println("You are blocked");
                                }
                                else if (user.isOwner()) {
                                    System.out.println("Welcome admin");
                                    int stepCodeAdmin = 1;
                                    while (stepCodeAdmin != 0) {
                                        System.out.println("1. show users\t2. block user\t3. unblock user\t4. delete user\t5. change password\t0. Exit");
                                        stepCodeAdmin = scannerInt.nextInt();
                                        switch (stepCodeAdmin) {
                                            case 1 -> {
                                                List<User> userList = userService.get();
                                                for (User user1 : userList) {
                                                    System.out.println(user1.toString());
                                                }
                                            }
                                            case 2 -> {
                                                System.out.println("Enter blocking user phoneNumber: ");
                                                User blockingUser = userService.getUserByPhoneNumber(scannerStr.nextLine());
                                                if (blockingUser != null) {
                                                    String blocked = userService.blockUser(blockingUser.getId());
                                                    System.out.println(blocked);
                                                }else System.out.println("User not found");
                                            }
                                            case 3 -> {
                                                System.out.println("Enter unblocking user phoneNumber: ");
                                                User unBlockingUser = userService.getUserByPhoneNumber(scannerStr.nextLine());
                                                if (unBlockingUser != null) {
                                                    String unBlocked = userService.blockUser(unBlockingUser.getId());
                                                    System.out.println(unBlocked);
                                                }else System.out.println("User not found");
                                            }
                                            case 4 -> {
                                                System.out.println("Enter deleting user phoneNumber: ");
                                                User deletingUser = userService.getUserByPhoneNumber(scannerStr.nextLine());
                                                if (deletingUser != null) {
                                                    String deleted = userService.deleteById(deletingUser.getId());
                                                    System.out.println(deleted);
                                                }
                                                else System.out.println("user not found!");
                                            }
                                            case 5 -> {
                                                System.out.println("Enter new password:");
                                                String newPassword = scannerStr.nextLine();
                                                String changedPassword = userService.changePassword(user.getId(), newPassword);
                                                System.out.println(changedPassword);
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Welcome " + user.getName() + " to online chat");
                                    int stepCodeUser = 1;
                                    while (stepCodeUser != 0) {
                                        System.out.println("1. send message to user");
                                        System.out.println("2. send message to group");
                                        System.out.println("3. create group");
//                                        System.out.println("4. join group");
                                        System.out.println("5. add people to group");
                                        System.out.println("6. change password");
                                        System.out.println("0. Exit");
                                        stepCodeUser = scannerInt.nextInt();
                                        switch (stepCodeUser) {
                                            case 1 -> {
                                                System.out.println("Enter messaging user phone number: ");
                                                User toMessageUser = userService.getUserByPhoneNumber(scannerStr.nextLine());
                                                if (toMessageUser != null) {
                                                    System.out.println("from: " + user.getName());
                                                    System.out.println("to: " + toMessageUser.getName());
                                                    System.out.println("All messages: \n");
                                                    System.out.println("I sent: \n");
                                                    List<Message> allSentMessages = messageService.showUsersChatMessages(user.getId(), toMessageUser.getId());
                                                    if (allSentMessages == null || allSentMessages.size() == 0) {
                                                        System.out.println("You have not sent sms to this user yet");
                                                    }
                                                    else {
                                                        for (Message message : allSentMessages) {
                                                            System.out.println(message.toString());
                                                        }
                                                    }
                                                    System.out.println("\n\n I received: \n");
                                                    List<Message> allReceivedMessages = messageService.showUsersChatMessages(toMessageUser.getId(), user.getId());
                                                    if (allReceivedMessages == null || allReceivedMessages.size() == 0) {
                                                        System.out.println("You have not received sms to this user yet");
                                                    }
                                                    else {
                                                        for (Message message : allReceivedMessages) {
                                                            System.out.println(message.toString());
                                                        }
                                                    }
                                                    Message message = new Message();
                                                    System.out.println("Enter new message title: ");
                                                    message.setName(scannerStr.nextLine());
                                                    System.out.println("Enter new message content: ");
                                                    message.setMessageContent(scannerStr.nextLine());
                                                    message.setFromUserId(user.getId());
                                                    message.setToUserId(toMessageUser.getId());
                                                    String send = messageService.add(message);
                                                    System.out.println(send);

                                                } else System.out.println("User not found");
                                            }
                                            case 2 -> {
                                                System.out.println("Enter group name: ");
                                                Group group = groupService.getGroup(scannerStr.nextLine());
                                                if (group != null) {
                                                    boolean checkUser = groupService.checkUserInGroup(group, user);
                                                    if (checkUser) {
                                                        System.out.println("\nChat history in " + group.getName() + "\n\n");
                                                        List<Message> allGroupMessages = messageService.getMessagesInGroup(group.getId());
                                                        if (allGroupMessages == null || allGroupMessages.size() == 0) {
                                                            System.out.println("no history in group");
                                                        }
                                                        else {
                                                            for (Message message : allGroupMessages) {
                                                                System.out.println(message.toString());
                                                            }
                                                        }
                                                        Message message = new Message();
                                                        System.out.println("Enter message title: ");
                                                        message.setName(scannerStr.nextLine());
                                                        System.out.println("enter message content: ");
                                                        message.setMessageContent(scannerStr.nextLine());
                                                        message.setFromUserId(user.getId());
                                                        message.setToUserId(group.getId());
                                                        messageService.add(message);
                                                    } else {
                                                        System.out.println("You are not allowed to send sms to this group");
                                                        System.out.println("Please, join first");
                                                    }
                                                }else System.out.println("Group not found");
                                            }
                                            case 3 -> {
                                                System.out.println("Enter new group name: ");
                                                String groupName = scannerStr.nextLine();
                                                Group newGroup = new Group();
                                                newGroup.setName(groupName);
                                                newGroup.setAdminId(user.getId());
                                                String createdGroup = groupService.add(newGroup);
                                                groupService.addUserToGroup(newGroup, user);
                                                System.out.println(createdGroup);
                                            }
                                            case 5 -> {
                                                System.out.println("Enter group name: ");
                                                Group group = groupService.getGroup(scannerStr.nextLine());
                                                if (group != null && group.getAdminId().equals(user.getId())) {
                                                    System.out.println("Enter user phone number");
                                                    User addingUser = userService.getUserByPhoneNumber(scannerStr.nextLine());
                                                    if (addingUser != null) {
                                                        String added = groupService.addUserToGroup(group, addingUser);
                                                        System.out.println(added);
                                                    }else System.out.println("User not found");
                                                } else System.out.println("Group is not found or you are not admin");
                                            }
                                            case 6 -> {
                                                System.out.println("Enter new password: ");
                                                String newPassword = scannerStr.nextLine();
                                                String changed = userService.changePassword(user.getId(), newPassword);
                                                System.out.println(changed);
                                            }
                                        }
                                    }
                                }
                            } else System.out.println("Wrong password");
                        } else System.out.println("You are not with us yet, please register first!");
                    }
                    case 2 -> {
                        if (!isExist) {
                            User user = new User();
                            System.out.println("What is your name: ");
                            user.setName(scannerStr.nextLine());
                            user.setPhoneNumber(phoneNumber);
                            System.out.println("Create password: ");
                            user.setPassword(scannerStr.nextLine());
                            user.setActive(true);
                            userService.add(user);
                            System.out.println("Successfully registered");
                        } else System.out.println("You are already registered!");
                    }
                }
            }
        }
    }
}
