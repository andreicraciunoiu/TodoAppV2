package home.example.projects.loginapp;


class Task {
    private int id;
    private String task;

    public Task(){
    }
    public Task(int id, String task){
        this.id = id;
        this.task = task;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    String getTask() {
        return task;
    }
}