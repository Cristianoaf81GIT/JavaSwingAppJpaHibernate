/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.profcristianoaf81.appswingjpa.views;

import br.com.profcristianoaf81.appswingjpa.dao.JpaDaoHibernateDerby;
import br.com.profcristianoaf81.appswingjpa.models.Carro;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author cristianoaf81
 */
public final class CarTableClass extends JFrame{
    private DefaultTableModel dtm, model_of_table;
    private JTable car_table;
    private JScrollPane table_scroll;
    private JLabel table_label,brand_label,model_label,date_label;
    private JpaDaoHibernateDerby jpa_obj_table;
    private List<Carro> carros;
    private Font table_font;
    private Container container;
    private JPanel panel;
    private JButton save_button,delete_button,exportXLS_btn;
    private JTextField brand,model,date_assemble,carId;
    private MaskFormatter formatter_mask;
    private SimpleDateFormat sdf;
    private Carro car;
    
    
    public CarTableClass() throws FontFormatException
            , IOException, ParseException{
        
     InitTableWindowComponents();
     setTableWindowLayout();
     setTableWindowEvents();
     
    }
    
    public void InitTableWindowComponents() 
            throws FontFormatException, IOException, ParseException{
        
        //setTableWindowLaF();
        setLayout(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle("Tabela de Veículos");
        dtm = new DefaultTableModel(new Object[]{"Código:",
        "Marca:","Modelo:","Data de Fabricação:"}, 0);
        table_label = new JLabel("Carros:",SwingConstants.CENTER);
        table_label.setOpaque(true);
        table_label.setBackground(new Color(30, 144, 255));
        table_label.setForeground(Color.WHITE);
        table_font  = Font.createFont(NORMAL
                , CarTableClass.class.getResourceAsStream(
                        "/br/com/profcristianoaf81/appswingjpa/"
                                 + "resources/AlexBrush-Regular.ttf"
                ));
        
        table_label.setFont(table_font.deriveFont(
                Font.TRUETYPE_FONT
                , 38)
        );
        
        car_table = new JTable(dtm);
        car_table.setSelectionBackground(new Color(30, 144, 255));
        table_scroll = new JScrollPane();
        table_scroll.setVerticalScrollBarPolicy(
          JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );
        LoadTableData();
        table_scroll.setViewportView(car_table);
        panel = new JPanel();
        panel.setBackground(new Color(30, 144, 255));
        panel.setLayout(null);
        brand_label = new JLabel("Marca:");
        brand_label.setForeground(Color.WHITE);
        brand = new JTextField();
        brand.setToolTipText("Redefina a marca...");
        model_label = new JLabel("Modelo:");
        model_label.setForeground(Color.WHITE);
        model = new JTextField();
        model.setToolTipText("Redefina o modelo...");
        date_label = new JLabel("Data de Fabricação:");
        date_label.setForeground(Color.WHITE);
        formatter_mask = new MaskFormatter("##/##/####");
        date_assemble = new JFormattedTextField(formatter_mask);
        date_assemble.setToolTipText("Redefina a data de fabricação...");
        carId = new JTextField();
        carId.setVisible(false);
        save_button = new JButton("Atualizar");
        delete_button = new JButton("Deletar");
        exportXLS_btn = new JButton("Exportar para Excel");
        brand.setEditable(false);
        model.setEditable(false);
        date_assemble.setEditable(false);
        panel.add(brand_label);
        panel.add(brand);
        panel.add(model_label);
        panel.add(model);
        panel.add(date_label);
        panel.add(date_assemble);
        panel.add(carId);
        panel.add(save_button);
        panel.add(delete_button);
        panel.add(exportXLS_btn);
        add(table_label);
        add(table_scroll);
        add(panel);
        
        container = getContentPane();
        
        
    
    }
    
    public void setTableWindowLayout(){
        setBounds(0, 0, 900, 550);
        setLocationRelativeTo(null);
        table_label.setBounds(
                  5
                , 20
                , 880
                , 50
        );
        
        car_table.setBounds(
                  10
                , 70
                , 500
                , 350
        );
        
        table_scroll.setBounds(
                  10
                , 80
                , 640
                , 400
        );
        
        panel.setBounds(
                  650
                , 80
                , 240
                , 410
        );
        brand_label.setBounds(25, 5, 120, 35);
        brand.setBounds(25,40,180,35);
        model_label.setBounds(25,75,120,35);
        model.setBounds(25,110,180,35);
        date_label.setBounds(25, 145, 180, 35);
        date_assemble.setBounds(25, 180, 180, 35);
        carId.setBounds(25, 215, 180, 35);
        save_button.setBounds(
                (panel.size().width-save_button.size().width)/8
                , 250
                , 180
                , 35
        );
        delete_button.setBounds(
                 (panel.size().width-delete_button.size().width)/8
                , 285
                , 180
                , 35
        );
        
        exportXLS_btn.setBounds(
               (panel.size().width-exportXLS_btn.size().width)/8
                , 320
                , 180
                , 35
        );
        
    }
    
    public void setTableWindowLaF(){
         //UIManager.getSystemLookAndFeelClassName()
        try {
            UIManager.setLookAndFeel(
             UIManager.getSystemLookAndFeelClassName()                   
            );
        } catch (ClassNotFoundException 
                | IllegalAccessException 
                | InstantiationException 
                | UnsupportedLookAndFeelException e) {
            
            try {
                UIManager.setLookAndFeel(
                 UIManager.getCrossPlatformLookAndFeelClassName()
                );
            } catch (ClassNotFoundException 
                    | IllegalAccessException 
                    | InstantiationException 
                    | UnsupportedLookAndFeelException ex) {
                
                System.out.println(ex.getMessage());
            }
                
        }
    }
    
    public void LoadTableData(){
      jpa_obj_table = new JpaDaoHibernateDerby();
      carros = jpa_obj_table.listar();
      model_of_table = (DefaultTableModel) car_table.getModel();
      carros.forEach((c) -> {
          model_of_table.addRow(new Object[]{
              c.getId(),
              c.getMarca(),
              c.getModelo(),
              c.getData_formatada()});
        });
        
       //desabilita edição em todas as linhas 
       for( int i = 0; i < car_table.getColumnCount(); i++ )
       {
           Class<?> tbl_col_class = car_table.getColumnClass(i);
           car_table.setDefaultEditor(tbl_col_class, null);
       }
      
       
    }
    
    public void setTableWindowEvents(){
       this.car_table.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               car = new Carro();
               int row = car_table.getSelectedRow();
               
               car.setId(Long.parseLong(
                    car_table.getModel().getValueAt(row, 0).toString()
               ));
               car.setMarca(car_table.getModel().getValueAt(row, 1)
                       .toString());
               car.setModelo(car_table.getModel().getValueAt(row, 2)
                       .toString());
               sdf = new SimpleDateFormat("dd/MM/yyyy"
                       ,Locale.getDefault());
               
               try {
                   //System.out.println(car_table.getModel()
                   //        .getValueAt(row, 3).toString());
                   car.setData_fabricacao(sdf.parse(
                           car_table.getModel().getValueAt(row, 3)
                                   .toString()
                   ));
                   
               } catch (ParseException ex) {
                   Logger.getLogger(
                           CarTableClass.class.getName()).
                           log(Level.SEVERE, null, ex);
               }
               brand.setEditable(true);
               model.setEditable(true);
               date_assemble.setEditable(true);
               carId.setText(car.getId().toString());
               brand.setText(car.getMarca());
               model.setText(car.getModelo());
               date_assemble.setText(car.getData_formatada());
               repaint();
               
           }

           @Override
           public void mousePressed(MouseEvent e) {
               
           }

           @Override
           public void mouseReleased(MouseEvent e) {
               
           }

           @Override
           public void mouseEntered(MouseEvent e) {
             
           }

           @Override
           public void mouseExited(MouseEvent e) {
              
           }
       });
       
       delete_button.addActionListener((e)->{
           int row = car_table.getSelectedRow();
           
           if(car.getId() == 0 | car.getId() == null)
           {
               JOptionPane.showMessageDialog(null
                       , "Por favor selecione um carro na lista"
                       +"\nantes de prosseguir!"
                       , "Aviso"
                       , JOptionPane.INFORMATION_MESSAGE
               );
           }else{
               
              Object[] options = {"Sim","Não"};
              int optType = JOptionPane.YES_NO_OPTION;
              int MessageType = JOptionPane.QUESTION_MESSAGE;
              Icon icon = null;
              int opc = JOptionPane.showOptionDialog(
                      null
                      , "Confirma remoção do registro?"
                      , "Confirmar"
                      , optType
                      , MessageType
                      ,icon
                      ,options
                      ,options[1]
              );
                      
               switch(opc){
                   case 0:
                     jpa_obj_table.deletar(car);
                     this.dtm.removeRow(row); 
                     repaint();
                     JOptionPane.showMessageDialog(null
                            , "Registro Removido com sucesso!"
                            , "Deletar"
                            , JOptionPane.INFORMATION_MESSAGE
                     );
                    break;
                   case 1:
                       //nada
                   break;    
                   default:
                       //nada
                   break;    
               }       
                      
            }
       });
       
       save_button.addActionListener((al)->{
         if(car.getId() == 0 | car.getId() == null)
           {
               JOptionPane.showMessageDialog(null
                       , "Por favor selecione um carro na lista"
                       +"\nantes de prosseguir!"
                       , "Aviso"
                       , JOptionPane.INFORMATION_MESSAGE
               );
           }else{ 
             
            Object[] options = {"Sim","Não"};
            int opc = JOptionPane.showOptionDialog(
                    null
                    , "Confirma Alteração?"
                    , "Confirmar"
                    , JOptionPane.YES_NO_OPTION
                    , JOptionPane.QUESTION_MESSAGE
                    , null
                    , options
                    , options[1]
            );
            switch(opc){
                case 0:
                    //btn sim
                    car.setId(Long.parseLong(carId.getText()));
                    car.setMarca(brand.getText());
                    car.setModelo(model.getText());
                    try{
                    car.setData_fabricacao(
                       sdf.parse(date_assemble.getText())
                    );
                    }catch(Exception ex){
                       ex.printStackTrace();
                    }
                    jpa_obj_table.atualizar(car);
                    JOptionPane.showMessageDialog(
                            null
                            , "Registro Atualizado com sucesso!"
                            , "Atualização"
                            , JOptionPane.INFORMATION_MESSAGE);
                    
                    dtm.setRowCount(0);
                    car_table.repaint();
                    LoadTableData();
                    car_table.repaint();    
                 break;
                case 1:
                    //btn não
                break;
                default:
                    //nao faz nada
                break;
            }
         }  
       });
       
       exportXLS_btn.addActionListener((l)->{
              JFileChooser fc = new JFileChooser();
              fc.setLocale(Locale.getDefault());
              fc.setDialogTitle("Save");
              FileNameExtensionFilter extension = 
                      new FileNameExtensionFilter("Excel xls", "xls");
              fc.addChoosableFileFilter(extension);
                int option = fc.showSaveDialog(CarTableClass.this);
                if(option == JFileChooser.APPROVE_OPTION){
                  String filename = fc.getSelectedFile().getName(); 
                    String path = fc.getSelectedFile()
                            .getParentFile().getPath();

			int len = filename.length();
			String ext = "";
			String file = "";

			if(len > 4){
                            ext = filename.substring(len-4, len);
			}

			if(ext.equals(".xls")){
                            file = path + "//" + filename; 
			}else{
                            file = path + "//" + filename + ".xls"; 
			}
                            toExcel(car_table, new File(file));
			}
       });
    }
    
    public void toExcel(JTable table, File file){
               
	try{
	TableModel model = table.getModel();
	FileWriter excel = new FileWriter(file);
	for(int i = 0; i < model.getColumnCount(); i++){
            excel.write(model.getColumnName(i) + "\t");
	}

	excel.write("\n");
	for(int i=0; i< model.getRowCount(); i++) {
        	for(int j=0; j < model.getColumnCount(); j++) {
                	excel.write(model.getValueAt(i,j)
                                               .toString()+"\t");
		}
		excel.write("\n");
        }

	excel.close();
	}catch(IOException e){ System.out.println(e); }
	
    }
  
    
}
