package com.cg.fitness.service;

import java.util.List;

import javax.persistence.PersistenceException;

import com.cg.fitness.Entity.Appointment;
import com.cg.fitness.Entity.Trainer;
import com.cg.fitness.Entity.User;
import com.cg.fitness.exception.AppointmentException;
import com.cg.fitness.exception.TrainerException;
import com.cg.fitness.exception.UserException;

public interface FitnessService {

	public Integer addAppointment(Appointment appointment) throws AppointmentException;
	public List<Appointment> viewAppointment() throws AppointmentException;
	public Appointment getAppointmentById(Integer appointment_id) throws  AppointmentException;
	public Integer deleteAppointment(Integer appointment_id) throws AppointmentException;
	public Integer addUser(User user) throws  UserException;
	public User getUserById(Integer user_id) throws  UserException;
	public Integer addTrainer(Trainer trainer) throws  TrainerException;
	public Trainer getTrainerById(Integer trainer_id) throws  TrainerException;
}



