package com.aditum.rehabicor.service;

import com.aditum.rehabicor.domain.AppUser;
import com.aditum.rehabicor.repository.AppUserRepository;
import com.aditum.rehabicor.service.dto.AppUserDTO;
import com.aditum.rehabicor.service.mapper.AppUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AppUser.
 */
@Service
@Transactional
public class AppUserService {

    private final Logger log = LoggerFactory.getLogger(AppUserService.class);

    private final AppUserRepository appUserRepository;

    private final AppUserMapper appUserMapper;

    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    /**
     * Save a appUser.
     *
     * @param appUserDTO the entity to save
     * @return the persisted entity
     */
    public AppUserDTO save(AppUserDTO appUserDTO) {
        log.debug("Request to save AppUser : {}", appUserDTO);
        AppUser appUser = appUserMapper.toEntity(appUserDTO);
        appUser = appUserRepository.save(appUser);
        return appUserMapper.toDto(appUser);
    }

    /**
     * Get all the appUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AppUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppUsers");
        return appUserRepository.findAll(pageable)
            .map(appUserMapper::toDto);
    }


    /**
     * Get one appUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AppUserDTO> findOne(Long id) {
        log.debug("Request to get AppUser : {}", id);
        return appUserRepository.findById(id)
            .map(appUserMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<AppUserDTO> findLoggedIn() {
        log.debug("Request to get AppUser Logged In");
        AppUser app = appUserRepository.findByUserIsCurrentUser().get(0);
        return Optional.of(appUserMapper.toDto(app));
    }

    @Transactional(readOnly = true)
    public Optional<AppUserDTO> findOneByUserId(Long userId) {
        log.debug("Request to get AppUser Logged In");
        return appUserRepository.findAppUserByUserId(userId)
            .map(appUserMapper::toDto);
    }
    /**
     * Delete the appUser by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AppUser : {}", id);
        appUserRepository.deleteById(id);
    }
}
