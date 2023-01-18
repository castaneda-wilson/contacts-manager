public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        phoneNumber = "(" + phoneNumber.substring(0,3) + ")" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
