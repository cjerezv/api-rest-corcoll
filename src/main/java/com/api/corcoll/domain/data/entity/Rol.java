package com.api.corcoll.domain.data.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="rol")
public class Rol implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_rol")
	@Getter	@Setter private Long id;
	
	@Column(unique=true, length=20)
	@Getter	@Setter private String nombre;

	private static final long serialVersionUID = 1L;
}
