package com.cg.fitness.pl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.log4j.Logger;

import com.cg.fitness.Entity.Appointment;
import com.cg.fitness.Entity.Trainer;
import com.cg.fitness.Entity.User;
import com.cg.fitness.exception.AppointmentException;
import com.cg.fitness.exception.TrainerException;
import com.cg.fitness.exception.UserException;
import com.cg.fitness.service.FitnessService;
import com.cg.fitness.service.FitnessServiceImpl;


public class FitnessCRUDTester {

	private static Scanner sc=new Scanner(System.in);
	private static Logger plLogger= Logger.getLogger(FitnessCRUDTester.class);
	private static FitnessService service= new FitnessServiceImpl();

	public static void main(String[] args) throws AppointmentException, UserException,TrainerException, ParseException {
		System.out.println("Welcome to Fitness App...\n\n\n");
		while(true) {
			System.out.println("\n\t\t************************************************************");
			System.out.println(" \t\t\t        Fitness App           ");
			System.out.println("\n\t\t************************************************************");
			System.out.println("\t\t\t1.Create Appointment");
			System.out.println("\t\t\t2.View All Appointment");
			System.out.println("\t\t\t3.Discard Appointment");
			System.out.println("\t\t\t4.Add User");
			System.out.println("\t\t\t5.Get Appointment By Id");
			System.out.println("\t\t\t6.Get User By Id");
			System.out.println("\t\t\t7.Add Trainer");
			System.out.println("\t\t\t8.Get Trainer By Id");
			System.out.println("\t\t\t9.Contact Us");
			System.out.println("\n\t\t************************************************************");
			System.out.println("Enter Choice :");
			int option= Integer.parseInt(sc.nextLine());
			try {
				switch(option) {
				case 1: Appointment appointment = new Appointment();
						addAppointment(appointment);
						System.out.println("1 Appointment added");
						plLogger.info("1 appointment added");
						break;
				case 2: List<Appointment> appointmentList= viewAppointment();
						appointmentList.stream().forEach(System.out::println);
						plLogger.info("Fetching all appointments");
						break;
				case 3: System.out.println("Enter Appointment id: ");
						Integer appointment_id = Integer.parseInt(sc.nextLine());
						int id= deleteAppointment(appointment_id);
						System.out.println("Appointment: "+id+" deleted");
						plLogger.info("Appointment: "+id+" deleted");
						break;
				case 4: User user1 = new User();
						addUser(user1);
						System.out.println("1 User added");
						plLogger.info("1 User added");
						break;
				case 5: System.out.println("Enter Get Appointment id : ");
						Integer appointment_id1 = sc.nextInt();
						appointment= getAppointmentById(appointment_id1); 
				
						try {
							if(appointment != null) {
								System.out.println(appointment);
								plLogger.info(appointment);
							}
							else {
								throw new AppointmentException("Invalid Appointment Id");
							}
						}
						catch(AppointmentException e) {
							plLogger.info(e);
						}
						break;
				case 6: System.out.println("Enter Get User id: ");
						Integer user_id = sc.nextInt();
						User user = new User();
						user= getUserById(user_id);
				
						try {
							if(user != null) {
								System.out.println(user);
								plLogger.info(user);
							}
							else {
								throw new UserException("Invalid user Id");
							}
						}
						catch(UserException e) {
							plLogger.info(e);
						}
						break;
				case 7: Trainer trainer = new Trainer();
						addTrainer(trainer);
						System.out.println("New Trainer Added");
						plLogger.info("1 trainer added");
						break;  
				case 8: System.out.println("Enter Get Trainer id : ");
						Integer trainer_id = sc.nextInt();
						Trainer trainer1 = new Trainer();
						trainer= getTrainerById(trainer_id);  
						try {
							if(trainer != null) {
								System.out.println(trainer);
								plLogger.info(trainer);
							}
							else {
								throw new TrainerException("Invalid trainer Id");
							}
						}
						catch(TrainerException e) {
							plLogger.info(e);
						}
						break;
				case 9: contactUs();
						break;
				default:System.out.println("Invalid option");
						break;
				}
			}catch(AppointmentException e) {
				plLogger.error(e.getMessage(),e);
			}
			catch(UserException e) {
				plLogger.error(e.getMessage(),e);
			}
			catch(TrainerException e) {
				plLogger.error(e.getMessage(),e);
			}

			System.out.println("Enter y to continue..");
			String ch=sc.next();
			sc.nextLine();
			if(!ch.equalsIgnoreCase("y")) {
				System.out.println("\n\n\n\t\t\t !!!!         Thank You     !!!!");
				break;
			}
		}
	}

	private static void addAppointment(Appointment appointment) throws AppointmentException, ParseException{
		System.out.println("Enter Appointment id:");
		appointment.setAppointment_id(sc.nextInt());

		System.out.println("Enter User id:");
		Integer uid = sc.nextInt();
		appointment.setUser_id(uid);

		System.out.println("Enter Trainer id:");
		Integer tid = sc.nextInt();
		appointment.setTrainer_id(tid);

		System.out.println("Enter Trainer Preference:");
		appointment.setTrainer_Preference(sc.next());

		System.out.println("Required phy_theropist (Yes/No) :");
		appointment.setPhy_the(sc.next());

		System.out.println("Enter Prefer Location :");
		appointment.setLocation(sc.next());

		try {
			System.out.println("Enter Appointmentdate(dd/MM/yyyy): ");
			String appointmentDate=sc.next();
			DateTimeFormatter formatter =
					DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate appointmentDate1= LocalDate.parse(appointmentDate, formatter);
			appointment.setDate(appointmentDate1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter Training Fee :");
		appointment.setAmount(sc.nextInt());

		service.addAppointment(appointment);
	}

	private static List<Appointment> viewAppointment() throws AppointmentException {
		return service.viewAppointment();
	}

	private static Appointment getAppointmentById(Integer appointment_id) throws AppointmentException{
		return service.getAppointmentById(appointment_id);        
	}

	private static int deleteAppointment(Integer appointment_id) throws AppointmentException {
		return service.deleteAppointment(appointment_id);
	}

	private static void addUser(User user) throws UserException{

		System.out.println("Enter User Id:");
		user.setUserId(sc.nextInt());

		System.out.println("Enter User fname:");
		user.setFname(sc.next());
		System.out.println("Enter User lname:");
		user.setLname(sc.next());

		System.out.println("Enter User Age:");
		user.setAge(sc.nextInt());

		System.out.println("Enter Mobile No:");
		user.setMobileNo(sc.nextLong());

		System.out.println("Enter Email Id:");
		user.setEmailId(sc.next());

		System.out.println("Enter Shipping Address:");
		user.setShippingAddress(sc.next());

		System.out.println("Enter User City:");
		user.setCity(sc.next());

		System.out.println("Enter User State:");
		user.setState(sc.next());

		System.out.println("Enter User Country:");
		user.setCountry(sc.next());

		System.out.println("Enter Pincode:");
		user.setPincode(sc.nextInt());

		service.addUser(user);        
	}

	private static User getUserById(Integer user_id) throws UserException{
		return service.getUserById(user_id);        
	}

	private static Trainer getTrainerById(Integer trainer_id) throws TrainerException{
		return service.getTrainerById(trainer_id);        
	}

	private static void addTrainer(Trainer trainer) throws TrainerException{

		System.out.println("Enter Trainer Id:");
		trainer.setTrainerId(sc.nextInt());

		System.out.println("Enter Trainer Name:");
		trainer.setTrainerName(sc.next());

		System.out.println("Enter Mobile No:");
		trainer.setMobileNo(sc.nextLong());

		System.out.println("Enter Email Id:");
		trainer.setEmailId(sc.next());

		System.out.println("Enter Address:");
		trainer.setAddress(sc.next());

		service.addTrainer(trainer);        
	}

	public static void contactUs() {

		System.out.println("\t\t\t\tWelcome to Fitness App...");
		System.out.println("\n\t\t************************************************************");
		System.out.println("\t\t\t#Contact Information");
		System.out.println("\t\t\tName      : Admin XYZ");
		System.out.println("\t\t\tMobile No : 7723455643");
		System.out.println("\t\t\tEmail Id  : ronaldo@gmail.com");
		System.out.println("\n\t\t************************************************************");
	}
}
