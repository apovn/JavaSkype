package fr.delthas.skype;

@SuppressWarnings({"javadoc", "static-method"})
public class TestConnect {

    public static final String USERNAME = "ductritran@gmail.com";
    public static final String PASSWORD = "";
    public static final String SEND_TO_ACCOUNT = "";

    // @Test (Disable it until a special test account is created)
    public void testConnect() {
        Skype skype = new Skype(USERNAME, PASSWORD);
        try {
//            Skype.setDebug(Paths.get("d:\\logfile.txt"));
            skype.connect();

            for (User user : skype.getContacts()) {
                System.out.println(user);
                if (user.getUsername().equalsIgnoreCase(transformUsername(SEND_TO_ACCOUNT))) {
                    user.sendMessage("Hi, " + user.getDisplayName() + ", what's up?");
                    System.out.println("Sent to: " + user.getUsername() + " successfully.");
                }
            }

//             for (Group group : skype.getGroups()) {
//                 Thread.sleep(2000);
//                 System.out.println("group: " + group.getTopic());

//                 if (group.isSelfAdmin()) {
//                     System.out.println("isSelfAdmin: " + group.getTopic());
// //                    skype.sendGroupMessage(group, "Hi everyone. Do not reply this msg.");
//                 }
//             }

        } catch (Exception e) {
            e.printStackTrace();
        }

        skype.setErrorListener(e -> {
            e.printStackTrace();
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
        }
        skype.disconnect();
    }

    private String transformUsername(String sendToAccount) {
        if ( sendToAccount.indexOf("@") > 0 ){
            sendToAccount = sendToAccount.substring(0, sendToAccount.indexOf("@"));
        }

        sendToAccount = "live:" + sendToAccount;

        return sendToAccount;
    }

    public static void main(String... strings) {
        new TestConnect().testConnect();
    }

}
