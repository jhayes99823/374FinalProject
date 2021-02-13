package testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddressSelectionBehavior;
import controller.Barista;
import controller.Database;
import controller.IfAnySelectionBehavior;
import controller.MachineTypeSelectionBehavior;
import controller.RequestManager;
import models.Address;
import models.Capability;
import models.Condiment;
import models.Drink;
import models.Machine;
import recipes.AddStep;
import recipes.MixStep;
import recipes.NullStep;
import recipes.Recipe;
import recipes.SteamStep;
import recipes.TopStep;

public class ProjectDemo {

	public static void main(String[] args) {
		RequestManager manager = new RequestManager();
		Drink drink = new Drink(0, new Address("", 0), "");
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		Box superBox = Box.createVerticalBox();
		Box orderBox = Box.createVerticalBox();
		superBox.add(orderBox);
		JTextField drinkField = new JTextField("Latte");
		orderBox.add(drinkField);
		Box addressBox = Box.createHorizontalBox();
		orderBox.add(addressBox);
		Box condimentBox = Box.createHorizontalBox();
		orderBox.add(condimentBox);
		Box recipeBox = Box.createHorizontalBox();
		orderBox.add(recipeBox);
		JTextField addressStreet = new JTextField("3 S. Walnut");
		addressBox.add(addressStreet);
		JTextField addressZIP = new JTextField("60601");
		addressBox.add(addressZIP);
		JTextField condimentName = new JTextField("Hazelnut");
		condimentBox.add(condimentName);
		JTextField condimentQty = new JTextField("2");
		condimentBox.add(condimentQty);
		JButton condimentAdd = new JButton("Add");
		condimentBox.add(condimentAdd);
		JButton condimentClear = new JButton("Clear");
		condimentBox.add(condimentClear);
		JTextField recipeStep = new JTextField("add");
		recipeBox.add(recipeStep);
		JTextField recipeObject = new JTextField("cream");
		recipeBox.add(recipeObject);
		JButton recipeAdd = new JButton("Add");
		recipeBox.add(recipeAdd);
		JButton recipeClear = new JButton("Clear");
		recipeBox.add(recipeClear);
		JButton orderButton = new JButton("Order");
		orderBox.add(orderButton);
		Box managerBox = Box.createVerticalBox();
		superBox.add(managerBox);
		Box selectorDefault = Box.createHorizontalBox();
		managerBox.add(selectorDefault);
		Box selectorIfAny = Box.createHorizontalBox();
		managerBox.add(selectorIfAny);
		JButton selectorClear = new JButton("Clear");
		managerBox.add(selectorClear);
		Box baristaBox = Box.createHorizontalBox();
		managerBox.add(baristaBox);
		JButton addressDefault = new JButton("Add Address Selector");
		selectorDefault.add(addressDefault);
		JButton typeDefault = new JButton("Add Type Selector");
		selectorDefault.add(typeDefault);
		JButton addressIfAny = new JButton("Add If Any Address Selector");
		selectorIfAny.add(addressIfAny);
		JButton typeIfAny = new JButton("Add If Any Type Selector");
		selectorIfAny.add(typeIfAny);
		JTextField baristaMachineNum = new JTextField("MID");
		baristaBox.add(baristaMachineNum);
		JButton baristaAdd = new JButton("Add Barista");
		baristaBox.add(baristaAdd);
		
		panel.add(superBox);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		condimentAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drink.getCondiments().add(new Condiment(condimentName.getText(), Integer.parseInt(condimentQty.getText())));
			}
		});
		condimentClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drink.getCondiments().clear();
			}
		});
		recipeAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (recipeStep.getText()) {
					case "steam":
						drink.setRecipe(new SteamStep(drink.getRecipe(), recipeObject.getText()));
						break;
					case "add":
						drink.setRecipe(new AddStep(drink.getRecipe(), recipeObject.getText()));
						break;
					case "mix":
						drink.setRecipe(new MixStep(drink.getRecipe()));
						break;
					case "top":
						drink.setRecipe(new TopStep(drink.getRecipe(), recipeObject.getText()));
						break;
				}
			}
		});
		recipeClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drink.setRecipe(new NullStep());
			}
		});
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						drink.setName(drinkField.getText());
						drink.getAddress().setStreet(addressStreet.getText());
						drink.getAddress().setZIP(Integer.parseInt(addressZIP.getText()));
						String requestString = "{\"order\":"+drink.toJSON().toString()+"}";
						drink.incrementOrderID();
						String response = manager.handleRequest(requestString);
						JOptionPane.showMessageDialog(frame, response);
					}
				}).start();
			}
		});
		addressDefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.addSelectionStrategy(new AddressSelectionBehavior());
			}
		});
		typeDefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.addSelectionStrategy(new MachineTypeSelectionBehavior());
			}
		});
		addressIfAny.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.addSelectionStrategy(new IfAnySelectionBehavior(new AddressSelectionBehavior()));
			}
		});
		typeIfAny.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.addSelectionStrategy(new IfAnySelectionBehavior(new MachineTypeSelectionBehavior()));
			}
		});
		selectorClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.clearSelectionStrategy();
			}
		});
		
		baristaAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Barista barista = new Barista(Database.getInstance().getMachines().get(Integer.parseInt(baristaMachineNum.getText())));
				barista.setSubject(manager.getSubject());
				createBarista(barista);
			}
		});
//		createBarista(new Barista(new Machine(0, null, null)));
	}
	
	public static void createBarista(Barista barista) {
		// Add some sort of barista updater thing
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		Box hbox = Box.createHorizontalBox();
		Box vbox = Box.createVerticalBox();
		JLabel machineLabel = new JLabel("Machine: " + barista.getMachine().getID());
		JLabel workingOnLabel = new JLabel("Working on: none");
		JLabel numInQueue = new JLabel("Number in Queue: " + barista.getNumInQueue());
		JButton finishWorking = new JButton("Finish Working");
		vbox.add(machineLabel);
		vbox.add(workingOnLabel);
		vbox.add(numInQueue);
		hbox.add(vbox);
		hbox.add(finishWorking);
		panel.add(hbox);
		frame.add(panel);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//System.out.println("Test");
				frame.dispose();
			}
		});
		frame.setVisible(true);
		
		finishWorking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				barista.finishDrink();
			}
		});
		barista.setCall(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				if(barista.isAttendingTo()) {
					workingOnLabel.setText("Working on: " + barista.currentlyWorkingOn().getOrderID());
				} else {
					workingOnLabel.setText("Working on: none");
				}
				numInQueue.setText("Number in Queue: " + barista.getNumInQueue());
				return null;
			}
		});
	}

}
