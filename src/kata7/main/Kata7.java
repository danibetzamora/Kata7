package kata7.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata7.control.BlockPresenter;
import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import kata7.model.Block;

public class Kata7 extends JFrame {
    public static void main(String[] args) {
        new Kata7().execute();
    }
    
    private BlockPanel blockDisplay;
    private Map<String, Command> commands = new HashMap<>();
    
    public Kata7() {
        this.setTitle("Block Shifter");
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        Block block = new Block(4, 4);
        BlockPresenter blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands.put("left", new LeftCommand(block));
        this.commands.put("right", new RightCommand(block));
        this.commands.put("up", new UpCommand(block));
        this.commands.put("down", new DownCommand(block));
    }

    private void execute() {
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel(Block.MAX);
        this.blockDisplay = blockPanel;
        return blockPanel;
    }

    private JMenuBar toolbar() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(button("left"));
        menubar.add(button("up"));
        menubar.add(button("down"));
        menubar.add(button("right"));
        return menubar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
            
        });
        return button;
    }
    
}