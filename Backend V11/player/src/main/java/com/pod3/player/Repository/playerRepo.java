package com.pod3.player.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pod3.player.Model.Player;

public interface playerRepo extends JpaRepository<Player,Integer> {

}
