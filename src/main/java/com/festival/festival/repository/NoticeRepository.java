package com.festival.festival.repository;

import com.festival.festival.entity.Notice;
import com.festival.festival.repository.custom.NoticeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long>, NoticeRepositoryCustom {

}
