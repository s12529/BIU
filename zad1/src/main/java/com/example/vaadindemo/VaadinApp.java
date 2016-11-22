package com.example.vaadindemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.vaadindemo.domain.Person;
import com.example.vaadindemo.service.PersonManager;
import com.example.vaadindemo.service.Sex;
import com.vaadin.annotations.Title;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Title("Vaadin Demo App")
public class VaadinApp extends UI {

	
	private PersonManager personManager = new PersonManager();
	private Person person = new Person("Jan", "Nowak", 1990, Sex.MALE);
	private BeanItem<Person> personItem = new BeanItem<Person>(person);
	private BeanItemContainer<Person> persons = new BeanItemContainer<Person>(Person.class);
	
	enum Action {
		EDIT, ADD;
	}

	private class MyFormWindow extends Window {
		private Action action;
		
		public MyFormWindow(Action act) {
			this.action = act;

			setModal(true);
			center();
			
			switch (action) {
			case ADD:
				setCaption("Dodaj nową osobę");
				break;
			case EDIT:
				setCaption("Edytuj osobę");
				break;
			default:
				break;
			}
			

			final FormLayout form = new FormLayout();
			final FieldGroup binder = new FieldGroup(personItem);

			final Button saveBtn = new Button("Dodaj osobę");
			final Button cancelBtn = new Button("Anuluj");
			
			final OptionGroup sex = new OptionGroup("Wybierz płeć");
			 sex.addItem(Sex.MALE);
			 sex.setItemCaption(Sex.MALE, Sex.MALE.getDesc());
			 sex.addItem(Sex.FEMALE);
			 sex.setItemCaption(Sex.FEMALE, Sex.FEMALE.getDesc());
			 sex.addItem(Sex.OTHER);
			 sex.setItemCaption(Sex.OTHER, Sex.OTHER.getDesc());
			 sex.select(Sex.MALE);
		     sex.setNullSelectionAllowed(false);
		     sex.setHtmlContentAllowed(true);
		     sex.setImmediate(true);
		     sex.setBuffered(true);
		     
		     sex.addValueChangeListener(e -> Notification.show("Value changed:",
		                String.valueOf(e.getProperty().getValue()),
		                Type.TRAY_NOTIFICATION));
		     
			
			form.addComponent(binder.buildAndBind("Imię", "firstName"));
			form.addComponent(binder.buildAndBind("Nazwisko", "lastName"));
			form.addComponent(binder.buildAndBind("Rok urodzenia", "yob"));


			binder.setBuffered(true);

			binder.getField("lastName").setRequired(true);
			binder.getField("firstName").setRequired(true);
			
	        form.addComponent(sex);
	        
	        final TextField questionField = new TextField();
	        questionField.setImmediate(true);
	        questionField.setInputPrompt("Jaki masz email?");
	        questionField.setCaption("Pytanie");
	        questionField.setRequired(true);
	        
	        form.addComponent(questionField);

			VerticalLayout fvl = new VerticalLayout();
			fvl.setMargin(true);
			fvl.addComponent(form);

			HorizontalLayout hl = new HorizontalLayout();
			hl.addComponent(saveBtn);
			hl.addComponent(cancelBtn);
			fvl.addComponent(hl);

			setContent(fvl);

			saveBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						binder.commit();

					if(validate(questionField)) {
						if (action == Action.ADD) {
							person.setSex((Sex) sex.getValue());
							personManager.addPerson(person);
						} else if (action == Action.EDIT) {
							person.setSex((Sex) sex.getValue());
							personManager.updatePerson(person);
						}
					
						persons.removeAllItems();
						persons.addAll(personManager.findAll());
						close();
					} else {
						 Notification sample = new Notification("Błąd",
					              "Nie możesz mieć takiego e-maila!");
							 sample.show(Page.getCurrent());
					}
					} catch (CommitException e) {
						e.printStackTrace();
					  }
					
				}
			});

			cancelBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					binder.discard();
					close();
				}
			});
		}
		
		private boolean validate(TextField tf) {
			
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcher = pattern.matcher(tf.getValue());
			if(matcher.find()) return true;
			else return false; 
		}
	}
	

	@Override
	protected void init(VaadinRequest request) {
		
		Button addPersonFormBtn = new Button("Dodaj");
		Button deletePersonFormBtn = new Button("Usuń");
		Button editPersonFormBtn = new Button("Edytuj");

		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		setContent(vl);
		
		addPersonFormBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new MyFormWindow(Action.ADD));
			}
		});

		editPersonFormBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new MyFormWindow(Action.EDIT));
			}
		});

		deletePersonFormBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!person.getFirstName().isEmpty()) {
					personManager.deletePerson(person);
					persons.removeAllItems();
					persons.addAll(personManager.findAll());
				}
			}
		});
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setMargin(true);
		hl.addComponent(addPersonFormBtn);
		hl.addComponent(editPersonFormBtn);
		hl.addComponent(deletePersonFormBtn);

		final Table personsTable = new Table("Ludziki", persons);
		
		personsTable.setColumnHeader("firstName", "Imię");
		personsTable.setColumnHeader("lastName", "Nazwisko");
		personsTable.setColumnHeader("yob", "Rok urodzenia");
		personsTable.setColumnHeader("id", "Identyfikator");
		personsTable.setColumnHeader("sex", "Płeć");
		personsTable.setVisibleColumns(new Object[] {"firstName", "lastName", "sex", "yob", "id"});
		personsTable.setSelectable(true);
		personsTable.setImmediate(true);

		personsTable.addValueChangeListener(new Property.ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {

				Person selectedPerson = (Person) personsTable.getValue();
				if (selectedPerson == null) {
					person.setFirstName("");
					person.setLastName("");
					person.setYob(0);
					person.setId(null);
					person.setSex(null);
				} else {
					person.setFirstName(selectedPerson.getFirstName());
					person.setLastName(selectedPerson.getLastName());
					person.setYob(selectedPerson.getYob());
					person.setId(selectedPerson.getId());
					person.setSex(selectedPerson.getSex());
				}
			}
		});

		vl.addComponent(hl);
		vl.addComponent(personsTable);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Label label = new Label();
		horizontalLayout.addComponent(label);
		//label.setValue(UI.getCurrent().toString());
		
		vl.addComponent(horizontalLayout);
		
		
		
	}

}
