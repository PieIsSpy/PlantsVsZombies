public class PlantsVsZombies {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        //view.changePanel("lawn");
        Controller controller = new Controller(model, view);
    }
}
