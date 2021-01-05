package com.cg.fitness.testing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cg.fitness.Entity.Appointment;
import com.cg.fitness.Entity.Trainer;
import com.cg.fitness.Entity.User;
import com.cg.fitness.dao.FitnessDaoImpl;
import com.cg.fitness.exception.AppointmentException;
import com.cg.fitness.exception.TrainerException;
import com.cg.fitness.exception.UserException;
import com.cg.fitness.service.FitnessServiceImpl;

@RunWith(MockitoJUnitRunner.class)



class FitnessTestCase {

	@InjectMocks
	private FitnessServiceImpl fitnessServiceImpl;
	@Mock
	private FitnessDaoImpl fitnessDaoImpl;

	@BeforeEach
	public void test()
	{
		MockitoAnnotations.initMocks(this);
	}

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Test
	public void testAddAppointment() throws AppointmentException {

		Appointment appointment = new Appointment();    
		appointment.setAppointment_id(123);
		appointment.setLocation("Pune");
		appointment.setDate(LocalDate.parse("10/12/2020", formatter));
		appointment.setAmount(2000);
		appointment.setPhy_the("yes");
		appointment.setTrainer_id(456);
		appointment.setUser_id(789);
		appointment.setTrainer_Preference("Female");

		fitnessServiceImpl.addAppointment(appointment);
		verify(fitnessDaoImpl,times(1)).addAppointment(appointment);
	}

	@Test
	public void testAddUser() throws UserException {

		User user = new User();    
		user.setFname("Prachi");
		user.setLname("Patil");
		user.setUserId(123);
		user.setAge(23);
		user.setCity("Pune");
		user.setCountry("India");
		user.setEmailId("abc@gmail.com");
		user.setMobileNo(9876543210L);
		user.setPincode(412413);
		user.setShippingAddress("Pimpri");
		user.setState("Maharashtra");
		fitnessServiceImpl.addUser(user);
		verify(fitnessDaoImpl,times(1)).addUser(user);
	}

	@Test
	public void testAddTrainer() throws TrainerException {

		Trainer trainer = new Trainer();    
		trainer.setTrainerId(234);
		trainer.setTrainerName("Sagar");
		trainer.setAddress("Pune");
		trainer.setEmailId("xyz@gmail.com");
		trainer.setMobileNo(9867452310L);
		fitnessServiceImpl.addTrainer(trainer);
		verify(fitnessDaoImpl,times(1)).addTrainer(trainer);
	}

	@Test
	public void testGetAppointmentById() throws AppointmentException {

		//Appointment appointment = new Appointment();
		Appointment appointment = null;
		appointment.setAppointment_id(12);
		if(fitnessServiceImpl !=null)
			fitnessServiceImpl.getAppointmentById(12);
		verify(fitnessDaoImpl,times(1)).getAppointmentById(12);
	}

	@Test
	public void testGetUserById() throws UserException {

		User user = new User();
		user.setUserId(123);
		if(fitnessServiceImpl !=null)
			fitnessServiceImpl.getUserById(123);
		verify(fitnessDaoImpl,times(1)).getUserById(123);
	}

	@Test
	public void testGetTrainerById() throws TrainerException {

		Trainer trainer = new Trainer();
		trainer.setTrainerId(101);
		if(fitnessServiceImpl !=null)
			fitnessServiceImpl.getTrainerById(101);
		verify(fitnessDaoImpl,times(1)).getTrainerById(101);
	}
}

