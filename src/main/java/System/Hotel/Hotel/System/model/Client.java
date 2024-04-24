package System.Hotel.Hotel.System.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import System.Hotel.Hotel.System.enums.Quartos;
import System.Hotel.Hotel.System.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;

import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull
    @NotBlank(message = "O nome Não pode estar em branco")
    private String nome;

    @Column(name = "checkin")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime checkin;

    @Column(name = "checkout")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime checkout;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "phone")
    @Size(min = 11, message = "É necessário Gerar o número de telefone Valido!!")
    @NotNull
    private String phone;

    @Column(name = "quartos")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Quartos quartos;

    @CPF
    @Column(name = "cpf")
    @NotNull
    private String cpf;

}
