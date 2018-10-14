/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.profcristianoaf81.appswingjpa.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.MaskFormatter;
import br.com.profcristianoaf81.appswingjpa.dao.JpaDaoHibernateDerby;
import br.com.profcristianoaf81.appswingjpa.models.Carro;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author cristianoaf81
 */
public final class CarWindowClass extends JFrame{
    private JTextField brand,model;
    private JFormattedTextField date_model;
    private MaskFormatter mf;
    private JButton save_btn,clear_btn,list_btn;
    private JLabel lb_brand,lb_model,lb_dtmodel;
    private SimpleDateFormat Dateformatter;
    private Date date_of_model;
    private CarTableClass carTable;
    
    public CarWindowClass() throws ParseException{
     setLookAndFeelWindow();
     StartWindowComponents();
     InitWindowLayout();
     DefineWindowCompEvents();
    }
    
    public void StartWindowComponents() throws ParseException{
        setLookAndFeelWindow();
        this.setBackground(Color.BLACK);
        setIconImage(
          Toolkit.getDefaultToolkit()
                  .getImage(
                     CarWindowClass.class.
                        getResource(
                         "/br/com/profcristianoaf81/appswingjpa/"
                                 + "resources/db.png"
                        )
        
        ));
        setTitle("Cadastro de Veículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(0, 0, 400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        brand = new JTextField(){
             @Override protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                  Graphics2D g2 = (Graphics2D) g.create();
                  g2.setPaint(getBackground());
                  g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                      0, 0, getWidth() - 1, getHeight() - 1));
                  g2.dispose();
                }
                super.paintComponent(g);
              }
              @Override public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
              }
            };
        
        
        brand.setToolTipText("Marca do Veículo...");
        model = new JTextField(){
             @Override protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                  Graphics2D g2 = (Graphics2D) g.create();
                  g2.setPaint(getBackground());
                  g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                      0, 0, getWidth() - 1, getHeight() - 1));
                  g2.dispose();
                }
                super.paintComponent(g);
              }
              @Override public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
              }
            };
        
        model.setToolTipText("Modelo do Veículo");
        mf = new MaskFormatter("##/##/####");
        date_model = new JFormattedTextField(mf){
             @Override protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                  Graphics2D g2 = (Graphics2D) g.create();
                  g2.setPaint(getBackground());
                  g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                      0, 0, getWidth() - 1, getHeight() - 1));
                  g2.dispose();
                }
                super.paintComponent(g);
              }
              @Override public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
              }
            };
        date_model.setToolTipText("Data de Fabricação");
        save_btn = new JButton("Salvar");
        clear_btn = new JButton("Limpar");
        list_btn = new JButton("Listar");
        lb_brand = new JLabel("Marca:");
        lb_model = new JLabel("Modelo:");
        lb_dtmodel = new JLabel("Data Fabricação:");
        add(lb_brand);
        add(brand);
        add(lb_model);
        add(model);
        add(lb_dtmodel);
        add(date_model);
        add(save_btn);
        add(clear_btn);
        add(list_btn);
        
        
    }
    
    public void InitWindowLayout(){
        
        lb_brand.setBounds(20, 20, 120, 35);
        brand.setBounds(20, 50, 180, 35);
        lb_model.setBounds(20, 80, 120, 35);
        model.setBounds(20, 110, 180, 35);
        lb_dtmodel.setBounds(20, 145, 120, 35);
        date_model.setBounds(20, 180, 120, 35);
        save_btn.setBounds(
                8
                , 230, 120, 35);
        clear_btn.setBounds(
               140, 230, 120, 35);
        list_btn.setBounds(
                clear_btn.getBounds().width+150, 230, 120, 35);
        
    }
    
    @SuppressWarnings("Convert2Lambda")
    public void DefineWindowCompEvents(){
        clear_btn.addActionListener((ActionEvent e) -> {
            brand.setText("");
            model.setText("");
            date_model.setText("");
        });
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(brand.getText().equals("")
                       ||model.getText().equals("")
                       ||date_model.getText().equals("")){
                   JOptionPane.showMessageDialog(null
                           , "Favor preencha todos os campos\n"
                           +"do formulário antes de prosseguir!"
                           , "Erro de Validação"
                           , JOptionPane.ERROR_MESSAGE);
               }else{
                   Carro carro = new Carro();
                   JpaDaoHibernateDerby jpa = new JpaDaoHibernateDerby();
                   Dateformatter = new SimpleDateFormat("dd/MM/yyyy"
                           , Locale.getDefault());
                   
                   carro.setMarca(brand.getText());
                   carro.setModelo(model.getText());
                   try {
                       date_of_model = Dateformatter.parse(
                               date_model.getText()
                       );
                   } catch (ParseException ex) {
                       Logger.getLogger(
                               CarWindowClass.class.getName())
                               .log(Level.SEVERE, null, ex);
                   }
                   carro.setData_fabricacao(date_of_model);
                   jpa.salvar(carro);
                   brand.setText("");
                   model.setText("");
                   date_model.setText("");
                   JOptionPane.showMessageDialog(null
                           , "Registro Gravado Com sucesso!"
                           , "Salvar Carro"
                           , JOptionPane.INFORMATION_MESSAGE);
                   try {
                       carTable = new CarTableClass();
                       carTable.setVisible(true);
                       
                   } catch (FontFormatException 
                           | IOException ex) {
                       Logger.getLogger(CarWindowClass.class.getName())
                               .log(Level.SEVERE, null, ex);
                   } catch (ParseException ex) {
                       Logger.getLogger(CarWindowClass.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   
               }
            }
        });
        list_btn.addActionListener((l)->{
            if(carTable == null){
                try {
                    carTable = new CarTableClass();
                } catch (FontFormatException 
                        | IOException | ParseException ex) {
                    Logger.getLogger(CarWindowClass.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                carTable.setVisible(true);
            }else{
                carTable.setVisible(true);
            }
        });
    }
    
    public void setLookAndFeelWindow(){
        //UIManager.getSystemLookAndFeelClassName()
        //com.jtattoo.plaf.noire.NoireLookAndFeel
        //com.jtattoo.plaf.hifi.HiFiLookAndFeel
        //net.infonode.gui.laf.InfoNodeLookAndFeel
        //com.seaglass.SeaGlassLookAndFeel
        //javax.swing.plaf.nimbus.NimbusLookAndFeel
        try {
            UIManager.setLookAndFeel(
               "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            );
                    
            
        } catch (ClassNotFoundException | IllegalAccessException 
                | InstantiationException 
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            try {
                UIManager.setLookAndFeel(
                        UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException 
                    | InstantiationException 
                    | UnsupportedLookAndFeelException ex) {
            }
        }
    }
class RoundedCornerBorder extends AbstractBorder {
  private final Color ALPHA_ZERO = new Color(0x0, true);
  @Override public void paintBorder(Component c
          , Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING
            , RenderingHints.VALUE_ANTIALIAS_ON);
    Shape border = getBorderShape(x, y, width - 1, height - 1);
    g2.setPaint(ALPHA_ZERO);
    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
    corner.subtract(new Area(border));
    g2.fill(corner);
    g2.setPaint(Color.GRAY);
    g2.draw(border);
    g2.dispose();
  }
  public Shape getBorderShape(int x, int y, int w, int h) {
    int r = h; //h / 2;
    return new RoundRectangle2D.Double(x, y, w, h, r, r);
  }
  @Override public Insets getBorderInsets(Component c) {
    return new Insets(4, 8, 4, 8);
  }
  @Override public Insets getBorderInsets(Component c, Insets insets) {
    insets.set(4, 8, 4, 8);
    return insets;
  }
}





}
