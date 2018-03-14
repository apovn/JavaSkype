package fr.delthas.skype;

import org.junit.Assert;

@SuppressWarnings({"javadoc", "static-method"})
public class TestConnect {

    public static final String USERNAME = "...";
    public static final String PASSWORD = "...";
    public static final String SEND_TO_ACCOUNT = "...";

    // @Test (Disable it until a special test account is created)
    public void testConnect() {
        Skype skype = new Skype(USERNAME, PASSWORD);
        try {
            skype.connect();

            for (User user : skype.getContacts()) {
                System.out.println("user: " + user);
                if (user.getUsername().equalsIgnoreCase(SEND_TO_ACCOUNT)) {
                    user.sendMessage("Hi, " + user.getDisplayName() + ", what's up?");
                }
            }

            for (Group group : skype.getGroups()) {
                Thread.sleep(2000);
                System.out.println("group: " + group.getTopic());

                if (group.isSelfAdmin()) {
                    System.out.println("isSelfAdmin: " + group.getTopic());
//                    skype.sendGroupMessage(group, "Hi everyone. Do not reply this msg.");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        skype.setErrorListener(e -> {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
        }
        skype.disconnect();
    }

    public static void main(String... strings) {
        new TestConnect().testConnect();
    }

}
