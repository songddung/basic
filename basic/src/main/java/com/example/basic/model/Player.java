package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data //toString() override
public class Player {
    @Id
    int playerId;
    String playerName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

//     public String toString(){
//         return "Player 내용: " + playerId + ", "+ playerName;
//     }
}
