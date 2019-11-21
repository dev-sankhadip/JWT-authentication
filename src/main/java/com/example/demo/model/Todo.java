package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Todo",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id"
        })
})
public class Todo {

    @Id
    private Long id;

    @NotBlank
    private String todo;

    private String date;

    public Todo(Long id, @NotBlank String todo, String date) {
        this.id = id;
        this.todo = todo;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
