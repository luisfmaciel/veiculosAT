package br.edu.infnet.veiculosservice;

import br.edu.infnet.veiculosservice.controller.VeiculoController;
import br.edu.infnet.veiculosservice.domain.Veiculo;
import br.edu.infnet.veiculosservice.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class VeiculoServiceTest {
    @Mock
    private VeiculoService veiculoService;
    @InjectMocks
    private VeiculoController veiculoController;

    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @BeforeEach
    public void setup() {
        veiculo1 = Veiculo.builder()
                .id("1")
                .modelo("Civic")
                .marca("Honda")
                .ano(2020)
                .build();

        veiculo2 = Veiculo.builder()
                .id("2")
                .modelo("Corolla")
                .marca("Toyota")
                .ano(2018)
                .build();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar todos os veículos registrados.")
    public void testListAll() {
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);
        when(veiculoService.listAll()).thenReturn(veiculos);

        List<Veiculo> result = veiculoController.listAll().getBody();

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getModelo(), "Civic");
        assertEquals(result.get(1).getModelo(), "Corolla");
    }

    @Test
    @DisplayName("Deve salvar um novo veículo.")
    public void testSaveVeiculo() {
        Veiculo newVehicle = new Veiculo();
        newVehicle.setModelo("Model S");
        newVehicle.setMarca("Tesla");
        newVehicle.setAno(2021);

        when(veiculoService.saveVeiculo(any(Veiculo.class))).thenReturn(newVehicle);

        Veiculo result = veiculoController.saveVeiculo(newVehicle).getBody();

        assertEquals(result.getModelo(), "Model S");
        assertEquals(result.getMarca(), "Tesla");
        verify(veiculoService, times(1)).saveVeiculo(newVehicle);
    }

    @Test
    @DisplayName("Deve atualizar um veículo existente.")
    public void testUpdateVeiculo() {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(veiculo1);

        when(veiculoService.updateVeiculo(eq("1"), any(Veiculo.class))).thenAnswer(invocation -> {
            Veiculo updatedVeiculo = invocation.getArgument(1);
            veiculos.set(0, updatedVeiculo);
            return updatedVeiculo;
        });

        veiculo1.setModelo("Dolphin");
        veiculo1.setMarca("BYD");

        ResponseEntity<Veiculo> result = veiculoController.updateVeiculo("1", veiculo1);

        assertEquals("Dolphin", result.getBody().getModelo());
        assertEquals("BYD", result.getBody().getMarca());

        verify(veiculoService, times(1)).updateVeiculo(eq("1"), any(Veiculo.class));
    }

    @Test
    @DisplayName("Deve deletar um veículo existente e retornar uma mensagem de sucesso.")
    public void testDeleteVehicle() {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(veiculo1);

        doAnswer(invocation -> {
            String id = invocation.getArgument(0);
            veiculos.removeIf(v -> v.getId().equals(id));
            return null;
        }).when(veiculoService).deleteVeiculo(eq("1"));

        ResponseEntity<Map<String, String>> response = veiculoController.deleteVeiculo("1");

        assertEquals(200, response.getStatusCodeValue());

        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Veiculo excluído com sucesso", response.getBody().get("message"));

        assertTrue(veiculos.isEmpty());

        verify(veiculoService, times(1)).deleteVeiculo(eq("1"));
    }
}

