package org.youcode.CITRONIX.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.youcode.CITRONIX.app.DTOs.field.NestedFieldDTO;
import org.youcode.CITRONIX.app.DTOs.tree.CreateTreeDTO;
import org.youcode.CITRONIX.app.DTOs.tree.TreeResponseDTO;
import org.youcode.CITRONIX.app.Mappers.tree.CreateTreeDTOToTreeEntityMapper;
import org.youcode.CITRONIX.app.Mappers.tree.TreeEntityToTreeResponseDTOMapper;
import org.youcode.CITRONIX.app.exceptions.EntityNotFoundException;
import org.youcode.CITRONIX.app.exceptions.FieldMaxDensityReachedException;
import org.youcode.CITRONIX.app.exceptions.InvalidPlantingDateException;
import org.youcode.CITRONIX.app.services.TreeServiceImp;
import org.youcode.CITRONIX.app.services.interfaces.FieldService;
import org.youcode.CITRONIX.app.services.interfaces.TreeService;
import org.youcode.CITRONIX.core.entities.Field;
import org.youcode.CITRONIX.core.entities.Tree;
import org.youcode.CITRONIX.infra.adapters.outbound.persistence.TreePersistenceAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TreeServiceImpTest {

    @Mock
    private TreePersistenceAdapter treePersistenceAdapter;
    @Mock
    private FieldService fieldService;
    @Mock
    private CreateTreeDTOToTreeEntityMapper createTreeDTOToTreeEntityMapper;
    @Mock
    private  TreeEntityToTreeResponseDTOMapper treeEntityToTreeResponseDTOMapper;
    @InjectMocks
    private TreeServiceImp treeService;

    @Test
    public void testCreateTree_success(){
        Field f = new Field();
        f.setId(1L);
        f.setSurface(1000);
        f.setTrees(new ArrayList<>());
        when(fieldService.getFieldEntityById(1L)).thenReturn(f);

        CreateTreeDTO createTreeDTO = new CreateTreeDTO(LocalDate.of(2022 , 5 , 31), 1L);
        Tree tree = new Tree();
        tree.setField(f);
        Tree createdTree = new Tree();
        when(createTreeDTOToTreeEntityMapper.toEntity(createTreeDTO)).thenReturn(tree);
        when(treePersistenceAdapter.save(tree)).thenReturn(createdTree);

        TreeResponseDTO treeResponseDTO =  new TreeResponseDTO(12L, LocalDate.of(2022, 5, 31), new NestedFieldDTO(f.getSurface(), new ArrayList<>()));
        when(treeEntityToTreeResponseDTOMapper.entityToDto(createdTree)).thenReturn(treeResponseDTO);

        TreeResponseDTO res = treeService.save(createTreeDTO);

        assertNotNull(res);
        assertEquals(treeResponseDTO, res);
        verify(treePersistenceAdapter , times(1)).save(tree);

    }

    @Test
    public void testCreateTree_FieldDoesntExists(){
        Long fieldId = 1L;
        LocalDate plantingDate = LocalDate.of(2022, 4, 15);
        CreateTreeDTO createTreeDTO = new CreateTreeDTO(plantingDate, fieldId);

        when(fieldService.getFieldEntityById(fieldId))
                .thenThrow(new EntityNotFoundException("No field found with given ID !"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            treeService.save(createTreeDTO);
        });

        assertEquals("No field found with given ID !", exception.getMessage());

        verify(fieldService, times(1)).getFieldEntityById(fieldId);
        verifyNoInteractions(createTreeDTOToTreeEntityMapper, treePersistenceAdapter, treeEntityToTreeResponseDTOMapper);
    }

    @Test
    public void testCreateTree_FieldMaxDensityReached() {
        Long fieldId = 1L;
        LocalDate plantingDate = LocalDate.of(2022, 4, 15);

        Field field = new Field();
        field.setId(fieldId);
        field.setSurface(10000);
        int maxTrees = 100;
        ArrayList<Tree> existingTrees = new ArrayList<>();
        for (int i = 0; i < maxTrees; i++) {
            existingTrees.add(new Tree());
        }
        field.setTrees(existingTrees);

        when(fieldService.getFieldEntityById(fieldId)).thenReturn(field);

        CreateTreeDTO createTreeDTO = new CreateTreeDTO(plantingDate, fieldId);

        FieldMaxDensityReachedException exception = assertThrows(FieldMaxDensityReachedException.class, () -> {
            treeService.save(createTreeDTO);
        });

        assertEquals("This field already reached the limit of planting !", exception.getMessage());

        verify(fieldService, times(1)).getFieldEntityById(fieldId);
        verifyNoInteractions(createTreeDTOToTreeEntityMapper, treePersistenceAdapter, treeEntityToTreeResponseDTOMapper);
    }

    @Test
    public void testCreateTree_InvalidPlantingDate() {
        Long fieldId = 1L;
        LocalDate invalidPlantingDate = LocalDate.of(2022, 2, 15);

        Field field = new Field();
        field.setId(fieldId);
        field.setSurface(10000);
        field.setTrees(new ArrayList<>());

        when(fieldService.getFieldEntityById(fieldId)).thenReturn(field);

        CreateTreeDTO createTreeDTO = new CreateTreeDTO(invalidPlantingDate, fieldId);

        InvalidPlantingDateException exception = assertThrows(InvalidPlantingDateException.class, () -> {
            treeService.save(createTreeDTO);
        });

        assertEquals("Trees can only be planted between March and Mai", exception.getMessage());

        verify(fieldService, times(1)).getFieldEntityById(fieldId);
        verifyNoInteractions(createTreeDTOToTreeEntityMapper, treePersistenceAdapter, treeEntityToTreeResponseDTOMapper);
    }


}
