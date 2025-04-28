import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveChooser extends JFrame
{
    JPanel buttonPnl, titlePnl, textPnl;
    private JLabel titleLbl;
    private JButton startBtn, quitBtn;
    private JFileChooser fileChooser;
    private JTextArea filesArea;
    private JScrollPane filesScrollPane;

    public RecursiveChooser()
    {
        setTitle("Recursive File Chooser & Viewer");
        setSize(450,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        titlePnl = new JPanel();
        titleLbl = new JLabel("Recursive File Lister");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 20));

        textPnl = new JPanel(new BorderLayout());
        filesArea = new JTextArea();
        filesArea.setEditable(false);
        filesScrollPane = new JScrollPane(filesArea);

        titlePnl.add(titleLbl, BorderLayout.NORTH);

        textPnl.add(filesScrollPane, BorderLayout.CENTER);

        buttonPnl = new JPanel();
        startBtn = new JButton("Start");
        quitBtn = new JButton("Quit");

        buttonPnl.add(startBtn);
        buttonPnl.add(quitBtn);
        add(titlePnl, BorderLayout.NORTH);
        add(textPnl, BorderLayout.CENTER);
        add(buttonPnl, BorderLayout.SOUTH);

        startBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(RecursiveChooser.this);
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File selectedDir = fileChooser.getSelectedFile();
                    filesArea.setText("");
                    listFilesRecursive(selectedDir);
                }
            }
        });
        quitBtn.addActionListener(e -> System.exit(0));

    }
    private void listFilesRecursive(File dir)
    {
        File[] files = dir.listFiles();
        if(files != null)
        {
            for (File file : files)
            {
                if(file.isDirectory())
                {
                    filesArea.append("Directory: " + file.getAbsolutePath() + "\n");
                }
                else
                {
                    filesArea.append("File: " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            RecursiveChooser frame = new RecursiveChooser();
            frame.setVisible(true);
        });
    }
}
