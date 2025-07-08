public class PlantsVsZombies {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        ProgramGUI view = new ProgramGUI();
        Controller controller = new Controller(model,view);
    }
}
