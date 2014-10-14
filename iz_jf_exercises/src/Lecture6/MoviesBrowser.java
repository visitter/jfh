package Lecture6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MoviesBrowser extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel lbMovie;	
	private JTextField tfMovie;
	
	private JLabel lbYear;
	private JComboBox<Integer> cbYear;
	
	private JLabel lbGenre;
	private JComboBox<Genre> cbGenre;
	
	private JLabel lbDirector;	
	private JTextField tfDirector;
	
	private JLabel lbActor;	
	private JTextField tfActor;
	
	private JLabel lbRating;	
	private JRadioButton[] rbRating;
	private ButtonGroup rgRating;
	
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnNext;
	
	private JButton btnAddMovie;
	private JButton btnDelMovie;
		
	private JList<Movie> listMovies;
	private DefaultListModel<Movie> listModel;	
	private JScrollPane spPane;
	private ArrayList<Movie> movieList;
	private ListIterator<Movie> iterator;
	
	private final int lbW = 60;
	private final int lbH = 22;
	private final int fieldsLeft = 60;
	private final String cTitle = "Movie Browser";
	
	//init
	{
		lbMovie = new JLabel("Movie");
		lbMovie.setLocation(10, 10);
		lbMovie.setSize(lbW, lbH);		
		tfMovie = new JTextField();
		tfMovie.setLocation(fieldsLeft, 12);
		tfMovie.setSize(100, 20);
		
		lbDirector = new JLabel("Director");
		lbDirector.setLocation(10, 35);
		lbDirector.setSize(lbW, lbH);		
		tfDirector = new JTextField();
		tfDirector.setLocation(fieldsLeft, 37);
		tfDirector.setSize(100, 20);
		
		lbYear = new JLabel("Year");
		lbYear.setLocation(10, 60);
		lbYear.setSize(lbW, lbH);		
		cbYear = new JComboBox<Integer>();
		cbYear.setLocation(fieldsLeft, 62);
		cbYear.setSize(100, 20);
				
		lbGenre = new JLabel("Genre");
		lbGenre.setLocation(10, 85);
		lbGenre.setSize(lbW, lbH);		
		cbGenre = new JComboBox<Genre>();
		cbGenre.setLocation(fieldsLeft, 87);
		cbGenre.setSize(100, 20);
		
		lbActor	 = new JLabel("Actor");
		lbActor.setLocation(10, 110);
		lbActor.setSize(lbW, lbH);
		tfActor  = new JTextField();
		tfActor.setLocation(fieldsLeft, 112);
		tfActor.setSize(100, 20);
		
		lbRating = new JLabel("Rating");
		lbRating.setLocation(10, 135);
		lbRating.setSize(lbW, lbH);		
		
		rbRating = new JRadioButton[4];
		rgRating = new ButtonGroup();

		for( Integer i=0; i<4; i++ ){
			rbRating[i] = new JRadioButton("P "+i.toString());
			rgRating.add(rbRating[i]);
			rbRating[i].setSize(50, 22);			
			rbRating[i].setLocation( (i%2)>0?2*fieldsLeft:fieldsLeft, i>1?160:137);						
		}
		rbRating[0].setSelected(true);//default
		
		btnSave = new JButton("Save");		
		btnLoad = new JButton("Load");
		btnNext = new JButton("Next");
		btnAddMovie= new JButton("Add");
		btnDelMovie= new JButton("Del");
		
		btnSave.setSize(80, 20);
		btnLoad.setSize(80, 20);
		btnNext.setSize(80, 20);
		btnAddMovie.setSize(60, 22);
		btnDelMovie.setSize(60, 22);
		
		btnSave.setLocation(10, 200);
		btnLoad.setLocation(100, 200);
		btnNext.setLocation(190, 200);
		btnAddMovie.setLocation(165, 10);
		btnDelMovie.setLocation(230, 10);
		
		btnAddMovie.setFocusable(false);
		btnDelMovie.setFocusable(false);
				
		listModel = new DefaultListModel<Movie>();	
		
		listMovies = new JList<Movie>(listModel);
		listMovies.setLayoutOrientation(JList.VERTICAL);
		listMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		spPane = new JScrollPane(listMovies);
		spPane.setSize(230, 80);		
		spPane.setLocation(165, 40);
		
		movieList = new ArrayList<Movie>();
		iterator = movieList.listIterator();
	}
	public MoviesBrowser(){		
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setTitle(cTitle);
		
		add(lbMovie);
		add(tfMovie);
		add(lbDirector);
		add(tfDirector);
		add(lbYear);
		add(cbYear);
		add(lbGenre);
		add(cbGenre);
		add(lbActor);
		add(tfActor);
		add(lbRating);
		for( int i=0;i<4;i++){
			add(rbRating[i]);
		}
		add(btnLoad);
		add(btnSave);
		add(btnNext);
		add(btnAddMovie);
		add(btnDelMovie);
		add(spPane);
		
		addItemsInCombos();
		addListeners();
	}
	private void addItemsInCombos(){
		for(int i=1900; i<=Year.now().getValue(); i++){
			cbYear.addItem(i);
		}
		Genre[] vals = Genre.values();
		for(Genre val:vals){
			cbGenre.addItem(val);
		}
	}
	private boolean checkInfo(){
		if( tfMovie.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Invalid movie name", "Error", JOptionPane.ERROR_MESSAGE);
			tfMovie.requestFocus();
			return false;			
		}else if(tfDirector.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Invalid director name", "Error", JOptionPane.ERROR_MESSAGE);
			tfDirector.requestFocus();
			return false;
		}else if(cbYear.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this, "Invalid year", "Error", JOptionPane.ERROR_MESSAGE);
			cbYear.requestFocus();
			return false;
		}else if(cbGenre.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this, "Invalid genre type", "Error", JOptionPane.ERROR_MESSAGE);
			cbGenre.requestFocus();
			return false;
		}else if(tfActor.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Invalid actor name", "Error", JOptionPane.ERROR_MESSAGE);
			tfActor.requestFocus();
			return false;
		}		
		return true;
	}
	private void saveToFile(ArrayList<?> list, String filename){
		try {
			FileOutputStream stream = new FileOutputStream(filename);
			ObjectOutputStream obj = new ObjectOutputStream(stream);
			obj.writeObject(list);
			obj.close();
			JOptionPane.showMessageDialog(this, "File successfully saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private ArrayList<?> loadFromFile(String filename){
		try 
		{
			FileInputStream stream = new FileInputStream(filename);			
			ObjectInputStream obj = new ObjectInputStream(stream);
			ArrayList<?> rList;
			try {
				rList = (ArrayList<?>)obj.readObject();				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				obj.close();
				return null;
			}
			obj.close();
			JOptionPane.showMessageDialog(this, "File successfully loaded");
			return rList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	private int getSelectedRadioButton(){
		for( int i = 0; i<rgRating.getButtonCount(); i++ ){
			if( rbRating[i].isSelected()){
				return i;				
			}
		}
		return -1;
	}
	private void addListeners(){
		btnAddMovie.addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( checkInfo() ){
					
					movieList.add( 									
									new Movie(
												tfMovie.getText(),
												tfDirector.getText(),
												(Integer)cbYear.getSelectedItem(),
												(Genre)cbGenre.getSelectedItem(),
												tfActor.getText(),
												getSelectedRadioButton()
												)
								 );					
					listModel.addElement(movieList.get(movieList.size()-1));
					addMovieIterator();
				}
			}
		});
		btnDelMovie.addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
				if( listMovies.getSelectedIndex()>-1 ){
					movieList.remove(listModel.remove(listMovies.getSelectedIndex()));
				}
				addMovieIterator();
			}
		});
		btnSave.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				java.io.File file;
				if( jfc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					try {
						if( file.createNewFile()||file.exists()){
							saveToFile(
										movieList,
										file.getAbsolutePath()
										);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}				
			}
		});
		btnLoad.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				if( jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){					
					movieList.addAll(
										(ArrayList<Movie>)loadFromFile(
																		jfc.getSelectedFile().getAbsolutePath()
																	   )
									);
					listModel.clear();
					for(Movie mov:movieList){
						listModel.addElement(mov);						
					}
				}
				addMovieIterator();
				btnNext.doClick();
			}
		});
		btnNext.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				 
				if( iterator!=null){
					if( iterator.hasNext()){
						setMovieToForm((Movie)iterator.next());				
					}else{
						addMovieIterator();
					}
				}
			}
		});
	}
	private void setMovieToForm( Movie movie ){
		if( movie!=null){ 
			tfMovie.setText(movie.getName());
			tfDirector.setText(movie.getDirector());
			cbYear.setSelectedItem(movie.getYear());
			cbGenre.setSelectedItem(movie.getGenre());
			tfActor.setText(movie.getActor());
			rbRating[movie.getRating()].setSelected(true);
		}		
	}
	private void addMovieIterator(){
		if( !movieList.isEmpty() ){
			iterator = movieList.listIterator();
			btnNext.doClick();
		}
	}
}
