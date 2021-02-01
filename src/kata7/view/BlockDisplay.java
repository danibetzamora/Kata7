package kata7.view;

public interface BlockDisplay {
    void display(int x, int y);
    void on(Moved moved);
    
    interface Moved{
        void to(int x, int y);
    }
}
