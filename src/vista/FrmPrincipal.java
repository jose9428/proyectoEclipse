package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DenunciaDAO;
import modelo.Denuncia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setBounds(100, 100,889, 638);
		setExtendedState(FrmPrincipal.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		JMenu mnNewMenu_2 = new JMenu("Procesos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Denuncia");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDenuncia frm = new FrmDenuncia();
				desktopPane.add(frm);
				frm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		

		JMenu mnNewMenu_3 = new JMenu("Reportes");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Reporte Denuncias");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteIncidencias();
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Reporte Denuncias por Regionn");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteIncidenciasRegion();
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
	
	public void ReporteIncidencias() {
		 DenunciaDAO denunciaDAO = new DenunciaDAO();
		 ArrayList<Denuncia> lista = denunciaDAO.listarTodos();
	 
        try {

            String path = "src\\reporte\\reporteIncidencias.jasper";

            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile((path));

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));

            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
	}
	
	public void ReporteIncidenciasRegion() {
		DenunciaDAO denunciaDAO = new DenunciaDAO();
		ArrayList<Denuncia> lista = denunciaDAO.ListarReporteIncidenciasPorReg();
	 
       try {

           String path = "src\\reporte\\reporteRegion.jasper";

           JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile((path));

           JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));

           JasperViewer view = new JasperViewer(jprint, false);
           view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
           view.setVisible(true);
       } catch (JRException ex) {
           System.out.println(ex.getMessage());
           ex.printStackTrace();
       }
	}
}
