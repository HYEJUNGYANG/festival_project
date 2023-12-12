package com.festival.festival.service;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.dto.ReserveDTO;
import com.festival.festival.entity.Reserve;
import com.festival.festival.repository.ReserveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    private final ReserveRepository reserveRepository;

    @Override
    public ReserveDTO read(int num) {
        Optional<Reserve> result = reserveRepository.findById(num);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public void insertReserve(ReserveDTO reserveDTO) {
        reserveDTO.setNow_date(LocalDateTime.now());
        reserveRepository.insertReserv(reserveDTO);
    }

    @Override
    public PageResultDTO<ReserveDTO, Reserve> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("num").descending());
        Page<Reserve> result = reserveRepository.findAll( pageable);
        Function<Reserve, ReserveDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn );
    }

    @Override
    public Long count(String state, String state2) {
        Long reserveCount = reserveRepository.countBystate(state,state2);

        return reserveCount;
    }

    @Override
    public List<Reserve> getTop3List(String cancel) {
        List<Reserve> dto = null;

        dto = reserveRepository.findTop3ByOrderByIdDesc(cancel);

        return dto;
    }

    @Override
    public ReserveDTO getList(int num) {
        Optional<Reserve> result = reserveRepository.getList(num);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public void modifyReserve(ReserveDTO dto){
        reserveRepository.modifyById(dto);
    }

    @Override
    public void modifyReviewYon(ReserveDTO dto) {
        reserveRepository.modifyReviewYon(dto);
    }

    @Override
    public void modifyCancel(int num) {
        reserveRepository.modifyCancel(num);
    }

    @Override
    public List<Integer> getNumList() {
        return reserveRepository.getNumList();
    }

    @Override
    public List<Reserve> selectReserve(String id) {
        return reserveRepository.findById(id);
    }


}
