/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * panelGrafo.java
 *
 * Created on Nov 13, 2009, 8:54:10 PM
 */

package antframework.app;
import antframework.common.*;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author enriqueareyan
 */
public class panelGrafo extends javax.swing.JPanel {
    public Main parent;
    private Graph G;
    private DefaultTableModel modelResultados;
    private DefaultTableModel modelGrafo;
    public Traza T;

    /** Creates new form panelGrafo */
    public panelGrafo(Main parent,String name,Graph G) {
        initComponents();
        this.setName(name);
        this.parent = parent;
        this.G = G;
        /* Inicializar la ventana */
        //Esconder barra de progreso
        this.jProgressBar1.setVisible(false);
        //Inicializar Iteraciones y Hormigas
        int cantNodos = G.getM().getRows();
        selecIteraciones.setModel(new SpinnerNumberModel(1/*valor inicial*/,1/*min*/,10000/*max*/,1/*step*/));
        selecIteraciones.setSize(37, 28);
        selecHormigas.setModel(new SpinnerNumberModel(1/*valor inicial*/,1/*min*/,1000/*max*/,1/*step*/));
        selecHormigas.setSize(37, 28);
        //Inicializar Nido y Fuente
        selecNido.setModel(new SpinnerNumberModel(0/*valor inicial*/,0/*min*/,cantNodos-1/*max*/,1/*step*/));
        selecFuente.setModel(new SpinnerNumberModel(0/*valor inicial*/,0/*min*/,cantNodos-1/*max*/,1/*step*/));
        //Inicializar Parametros
        setTablaParametros(0);
        /* Iniciar el modelo de los resultados */
        this.modelResultados = new DefaultTableModel();
        this.modelResultados.addColumn("Algoritmo");
        this.modelResultados.addColumn("Solucion");
        this.modelResultados.addColumn("f(Solucion)");
        this.modelResultados.addColumn("Velocidad (segs)");
        /* Mostrar el grafo */
        this.modelGrafo = new DefaultTableModel();
        for(int i=0;i<cantNodos;i++){
            this.modelGrafo.addColumn(i);
        }
        for(int i=0;i<cantNodos;i++){
            Object[] fila = new Object[cantNodos];
            for(int j=0;j<cantNodos;j++){
                double valor = this.G.getM().position(i, j);
                fila[j] = (valor < Integer.MAX_VALUE) ? valor: -1;
            }
            this.modelGrafo.addRow(fila);
        }
        this.tablaGrafo.setModel(modelGrafo);
        //this.tablaGrafo.setEnabled(false);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGrafo = new javax.swing.JSplitPane();
        panelParametros = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        selecIteraciones = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        selecHormigas = new javax.swing.JSpinner();
        botonEjecutar = new javax.swing.JButton();
        selecAlgoritmos = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaParametros = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selecNido = new javax.swing.JSpinner();
        selecFuente = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        panelResultados = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaGrafo = new javax.swing.JTable();
        botonGuardarGrafo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Algoritmo:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Iteraciones:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Hormigas:");

        botonEjecutar.setText("Ejecutar");
        botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEjecutarActionPerformed(evt);
            }
        });

        selecAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "(SH) Sistema de Hormigas", "(SHE) Sistema de Hormigas Elite", "(SHRango) Sistema de Hormigas Rango", "(SHMM) Sistema de Hormigas Min-Max", "(SCH) Sistema Colonia de Hormigas", "(HQ) Hormiga Q" }));
        selecAlgoritmos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecAlgoritmosActionPerformed(evt);
            }
        });

        tablaParametros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tablaParametros);

        jLabel4.setText("Otros parametros:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nido:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Fuente:");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selecIteraciones, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(selecHormigas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(selecFuente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .add(selecNido, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel4))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(botonEjecutar))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(14, 14, 14)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .add(selecAlgoritmos, 0, 0, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {selecFuente, selecHormigas, selecIteraciones, selecNido}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(selecAlgoritmos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(selecIteraciones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selecHormigas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selecNido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selecFuente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(botonEjecutar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout panelParametrosLayout = new org.jdesktop.layout.GroupLayout(panelParametros);
        panelParametros.setLayout(panelParametrosLayout);
        panelParametrosLayout.setHorizontalGroup(
            panelParametrosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelParametrosLayout.setVerticalGroup(
            panelParametrosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelGrafo.setLeftComponent(panelParametros);

        panelResultados.setPreferredSize(new java.awt.Dimension(500, 505));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafo"));

        tablaGrafo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21", "Title 22", "Title 23", "Title 24", "Title 25", "Title 26", "Title 27"
            }
        ));
        tablaGrafo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tablaGrafo);

        botonGuardarGrafo.setText("Guardar");
        botonGuardarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarGrafoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .add(botonGuardarGrafo))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(botonGuardarGrafo)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Algoritmo", "Solucion", "f(Solucion)", "Velocidad (segs)"
            }
        ));
        jScrollPane3.setViewportView(tablaResultados);

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ver Traza");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jButton3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 216, Short.MAX_VALUE)
                        .add(jButton2)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton3)
                    .add(jButton2))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout panelResultadosLayout = new org.jdesktop.layout.GroupLayout(panelResultados);
        panelResultados.setLayout(panelResultadosLayout);
        panelResultadosLayout.setHorizontalGroup(
            panelResultadosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelResultadosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelResultadosLayout.setVerticalGroup(
            panelResultadosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelGrafo.setRightComponent(panelResultados);

        jButton4.setText("Cerrar Pestaña");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelGrafo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(698, Short.MAX_VALUE)
                .add(jButton4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(panelGrafo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton4)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setTablaParametros(int algoritmo){
        /* Cosas comunes a todos los parametros */
         DefaultTableModel M = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column == 1);
            }
        };
        M.addColumn("Parametro");
        M.addColumn("Valor");

        switch(algoritmo){
            case 0:
                M.addRow(new Object[]{"Tasa de evaporacion","0.6"});
                M.addRow(new Object[]{"Alfa","0.1"});
                M.addRow(new Object[]{"Beta","0.8"});
                break;
            case 1: case 2:
                M.addRow(new Object[]{"Tasa de evaporacion","0.6"});
                M.addRow(new Object[]{"Alfa","0.1"});
                M.addRow(new Object[]{"Beta","0.8"});
                M.addRow(new Object[]{"Epsilon","1"});
                break;
            case 3:
                M.addRow(new Object[]{"Tasa de evaporacion","0.6"});
                M.addRow(new Object[]{"Alfa","0.1"});
                M.addRow(new Object[]{"Beta","0.8"});
                M.addRow(new Object[]{"Tau Max","1.75"});
                M.addRow(new Object[]{"Tau Min","0.75"});
                break;
            case 4:
                M.addRow(new Object[]{"Beta","0.25"});
                M.addRow(new Object[]{"Candidatos","10"});
                M.addRow(new Object[]{"r_0","0.5"});
                M.addRow(new Object[]{"Tau_0","0.1"});
                M.addRow(new Object[]{"ro_1","0.1"});
                M.addRow(new Object[]{"ro_2","0.9"});
                break;
            case 5:
                M.addRow(new Object[]{"Beta","0.25"});
                M.addRow(new Object[]{"Candidatos","10"});
                M.addRow(new Object[]{"r_0","0.5"});
                M.addRow(new Object[]{"Tau_0","0.1"});
                M.addRow(new Object[]{"ro_1","0.1"});
                M.addRow(new Object[]{"ro_2","0.9"});
                M.addRow(new Object[]{"Gamma","0.5"});
                break;
        }
        tablaParametros.setModel(M);
        tablaParametros.getColumnModel().getColumn(0).setPreferredWidth(150);

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.crearPanelTraza();
}//GEN-LAST:event_jButton3ActionPerformed

    private void selecAlgoritmosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecAlgoritmosActionPerformed
        // TODO add your handling code here:
        setTablaParametros(selecAlgoritmos.getSelectedIndex());
}//GEN-LAST:event_selecAlgoritmosActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.parent.eliminarTab(this);
    }//GEN-LAST:event_jButton4ActionPerformed
    public void reportResults(String algo,String result,double f,long time){
        /* Reportar resultados */
        modelResultados.addRow(new Object[]{algo, result,f,time/1000.0});
        tablaResultados.setModel(modelResultados);
        //mover el focus de la tabla al ultimo recien creado
        tablaResultados.changeSelection(tablaResultados.getRowCount()+1, 0, false, false);
        tablaResultados.scrollRectToVisible(tablaResultados.getCellRect( tablaResultados.getRowCount()+1, 0, true ) );
        /* Esconder la barra de progreso y volver a mostrar el boton de ejecutar*/
        jProgressBar1.setVisible(false);
        botonEjecutar.setEnabled(true);
    }
    public void reportFailure(Exception e){
        /* Reportar algun fallo en la ejecucion del algoritmo */
        jProgressBar1.setVisible(false);
        botonEjecutar.setEnabled(true);
        //this.cajaError.setText(e.toString());
        //this.dialogoError.setSize(450, 325);
        //this.dialogoError.setVisible(true);
    }
    public void actualizarBarraProgreso(int value){
        this.jProgressBar1.setValue(value);
    }
    public void crearPanelTraza(){
        formTraza FT = new formTraza(T,this);
        FT.setVisible(true);

    }
    private void verificarCambioGrafo(){
        /* Verificar si el grafo cambio y cambiar el objeto G acorde
         * Esto permite modificar el grafo en linea...
         */
        double[] dataGrafo = new double[this.modelGrafo.getColumnCount()*this.modelGrafo.getRowCount()];
        double temp;
        int cantCols =this.modelGrafo.getColumnCount(),cantFilas = this.modelGrafo.getRowCount();
        for(int i=0;i<cantCols;i++){
            for(int j=0;j<cantFilas;j++){
                temp = Double.parseDouble(this.modelGrafo.getValueAt(i, j).toString());
                dataGrafo[i*cantCols + j] = (temp > 0) ? temp: Integer.MAX_VALUE;
            }
        }
        this.G.setM(new Matrix(this.modelGrafo.getColumnCount(),this.modelGrafo.getRowCount(),dataGrafo));
    }
    private void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
        // TODO add your handling code here:
        try{
            verificarCambioGrafo();
            /* Extraer parametros de la aplicacion */
            int iteraciones = Integer.parseInt(this.selecIteraciones.getValue().toString());
            int hormigas    = Integer.parseInt(this.selecHormigas.getValue().toString());
            int nido        = Integer.parseInt(this.selecNido.getValue().toString());
            int fuente      = Integer.parseInt(this.selecFuente.getValue().toString());
            /* Configurar y Mostrar la barra de progreso */
            this.jProgressBar1.setMaximum(iteraciones);
            this.jProgressBar1.setVisible(true);
            /* Esconder el boton de Ejecutar */
            this.botonEjecutar.setEnabled(false);
            this.T = new Traza();
            /* Ejecutar el algoritmo seleccionado*/
            switch(this.selecAlgoritmos.getSelectedIndex()){
                case 0:
                    AS AS = new AS(G,this,hormigas,nido,fuente,iteraciones,Double.parseDouble(tablaParametros.getValueAt(0, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(1, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(2, 1).toString()));
                    Thread TAS = new Thread(AS);
                    Thread TAS1 = new Thread(new CheckProgress(iteraciones,AS,TAS,this));
                    TAS.start();
                    TAS1.start();
                    break;
                case 1:
                    EAS EAS = new EAS(G,this,hormigas,nido,fuente,iteraciones,Double.parseDouble(tablaParametros.getValueAt(0, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(1, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(2, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(3, 1).toString()));
                    Thread TEAS = new Thread(EAS);
                    Thread TEAS1 = new Thread(new CheckProgress(iteraciones,EAS,TEAS,this));
                    TEAS.start();
                    TEAS1.start();
                    break;
                case 2:
                    RAS RAS = new RAS(G,this,hormigas,nido,fuente,iteraciones,Double.parseDouble(tablaParametros.getValueAt(0, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(1, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(2, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(3, 1).toString()));
                    Thread TRAS = new Thread(RAS);
                    Thread TRAS1 = new Thread(new CheckProgress(iteraciones,RAS,TRAS,this));
                    TRAS.start();
                    TRAS1.start();
                    break;
                case 3:
                    MMAS MMAS = new MMAS(G,this,hormigas,nido,fuente,iteraciones,Double.parseDouble(tablaParametros.getValueAt(0, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(1, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(2, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(3, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(4, 1).toString()));
                    Thread TMMAS = new Thread(MMAS);
                    Thread TMMAS1 = new Thread(new CheckProgress(iteraciones,MMAS,TMMAS,this));
                    TMMAS.start();
                    TMMAS1.start();
                    break;
                case 4:
                    ACS ACS = new ACS(G,this,hormigas,nido,fuente,iteraciones,Double.parseDouble(tablaParametros.getValueAt(0, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(1, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(2, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(3, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(4, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(5, 1).toString()));
                    Thread TACS = new Thread(ACS);
                    Thread TACS1 = new Thread(new CheckProgress(iteraciones,ACS,TACS,this));
                    TACS.start();
                    TACS1.start();
                    break;
                case 5:
                    ANTQ ANTQ = new ANTQ(G,this,hormigas,nido,fuente,iteraciones,Double.parseDouble(tablaParametros.getValueAt(0, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(1, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(2, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(3, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(4, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(5, 1).toString()),Double.parseDouble(tablaParametros.getValueAt(6, 1).toString()));
                    Thread TANTQ = new Thread(ANTQ);
                    Thread TANTQ1 = new Thread(new CheckProgress(iteraciones,ANTQ,TANTQ,this));
                    TANTQ.start();
                    TANTQ1.start();
                    break;
            }
        /* Reportar algun error en un dialog box */
        }catch(NumberFormatException e){
            this.parent.reportarError("Uno de los parametros introducidos no es válido:\n"+e.toString(),javax.swing.JOptionPane.ERROR_MESSAGE);
            this.botonEjecutar.setEnabled(true);
            this.jProgressBar1.setVisible(false);
        }catch(Exception e){
            this.parent.reportarError(e.toString(),javax.swing.JOptionPane.ERROR_MESSAGE);
            this.botonEjecutar.setEnabled(true);
            this.jProgressBar1.setVisible(false);
        }

    }//GEN-LAST:event_botonEjecutarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.modelResultados.setRowCount(0);
    }//GEN-LAST:event_jButton2ActionPerformed

	private void botonGuardarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarGrafoActionPerformed
		// TODO: hacer lo mismo que se hace antes de ejecutar y guardar el grafo en la misma ruta que tiene actualmente.
            try{
                verificarCambioGrafo();
                G.save(G.getFileSource());
                this.parent.reportarError("Grafo salvado exitosamente!", javax.swing.JOptionPane.PLAIN_MESSAGE);
            }catch(Exception e){
               this.parent.reportarError("Error guardando el grafo:\n"+e.toString(),javax.swing.JOptionPane.ERROR_MESSAGE);
            }
	}//GEN-LAST:event_botonGuardarGrafoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEjecutar;
    private javax.swing.JButton botonGuardarGrafo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane panelGrafo;
    private javax.swing.JPanel panelParametros;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JComboBox selecAlgoritmos;
    private javax.swing.JSpinner selecFuente;
    private javax.swing.JSpinner selecHormigas;
    private javax.swing.JSpinner selecIteraciones;
    private javax.swing.JSpinner selecNido;
    private javax.swing.JTable tablaGrafo;
    private javax.swing.JTable tablaParametros;
    private javax.swing.JTable tablaResultados;
    // End of variables declaration//GEN-END:variables

}
