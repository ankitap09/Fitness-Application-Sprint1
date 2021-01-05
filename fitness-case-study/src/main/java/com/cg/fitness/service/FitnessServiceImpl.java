package com.cg.fitness.service;

import java.util.List;

import javax.persistence.PersistenceException;

import com.cg.fitness.Entity.Appointment;
import com.cg.fitness.Entity.Trainer;
import com.cg.fitness.Entity.User;
import com.cg.fitness.dao.FitnessDao;
import com.cg.fitness.dao.FitnessDaoImpl;
import com.cg.fitness.exception.AppointmentException;
import com.cg.fitness.exception.TrainerException;
import com.cg.fitness.exception.UserException;


public class FitnessServiceImpl implements FitnessService{
	private  FitnessDao fitnessDao= new FitnessDaoImpl();
	
	/*
	 * Create Appointments and stored in database
	 */
	@Override
	public Integer addAppointment(Appointment appointment)throws AppointmentException{
		try {
			return fitnessDao.addAppointment(appointment);
		}
		catch(PersistenceException e) {
			throw new AppointmentException(e.getMessage(),e);
		}
	}
	

	/*
	 * Fetch All Appointments details
	 */
	@Override
	public List<Appointment> viewAppointment() throws AppointmentException {
		try {			
			List<Appointment> appointmentList= 
					fitnessDao.viewAppointment();
			return appointmentList;
		}catch(PersistenceException e) {
			throw new AppointmentException(e.getMessage(),e);
		}
	}

	/*
	 * Get Appointment details by Id
	 */
	@Override
	public Appointment getAppointmentById(Integer appointment_id) throws AppointmentException {
		try {			
			Appointment appointment= fitnessDao.getAppointmentById(appointment_id);
			return appointment;
		}catch(PersistenceException e) {
			throw new AppointmentException(e.getMessage(),e);
		}
	}

	/*
	 * Delete Appointment 
	 */
	@Override
	public Integer deleteAppointment(Integer appointment_id) throws AppointmentException {
		try {			
			Integer id= fitnessDao.deleteAppointment(appointment_id);
			return id;
		}catch(PersistenceException e) {
			throw new AppointmentException(e.getMessage(),e);
		}
	}
	
	/*
	 * Add user details in database
	 */
	@Override
	public Integer addUser(User user) throws UserException {
		try {
			return fitnessDao.addUser(user);			
		}catch(PersistenceException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	/*
	 * Get User Details By Id
	 */
	@Override
	public User getUserById(Integer user_id) throws UserException {
		try {			
			User user= fitnessDao.getUserById(user_id);
			return user;
		}catch(PersistenceException e) {
			throw new UserException(e.getMessage(),e);
		}
	}

	
	/*
	 * Add trainer details in database
	 */
	@Override
	public Integer addTrainer(Trainer trainer) throws TrainerException {
		try {
			return fitnessDao.addTrainer(trainer);			
		}catch(PersistenceException e) {
			throw new TrainerException(e.getMessage(),e);
		}
	}

	
	/*
	 * Get Trainer Details By Id
	 */
	@Override
	public Trainer getTrainerById(Integer trainer_id) throws TrainerException {
		try {			
			Trainer trainer = fitnessDao.getTrainerById(trainer_id);
			return trainer;
		}catch(PersistenceException e) {
			throw new TrainerException(e.getMessage(),e);
		}	
	}
}







