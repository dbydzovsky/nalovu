package cz.dbydzovsky.nalovu.repositories

import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.model.MoneyFight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository

@Repository
interface MoneyFightRepository : JpaRepository<MoneyFight, Long>