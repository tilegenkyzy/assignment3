public class Student {
    private String fullName;
    private int age;

    public Student(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    @Override
    public String toString() {
        return fullName + " (" + age + ")";
    }
}

