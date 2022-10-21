package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.TicketRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class TicketMapperImpl implements TicketRepository {

    @Override
    public void create(Ticket ticket, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            ticketRepository.create(ticket, aircarrierId);
        }
    }

    @Override
    public List<Ticket> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            return ticketRepository.readAll();
        }
    }

    @Override
    public Optional<Ticket> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            TicketRepository mapper = session.getMapper(TicketRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Ticket ticket, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            ticketRepository.update(ticket, aircarrierId);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            ticketRepository.deleteById(id);
        }
    }
}
