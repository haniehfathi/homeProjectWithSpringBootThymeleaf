package com.project.home.service;

import com.project.home.config.MyBeanCopy;
import com.project.home.entity.Home;
import com.project.home.entity.HomePictures;
import com.project.home.entity.User;
import com.project.home.repository.HomePicturesRepository;
import com.project.home.repository.HomeRepository;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private HomePicturesRepository homePicturesRepository;

    @Transactional
    public Home addHome(Home home) throws IOException, InvocationTargetException, IllegalAccessException {
    	if(home.getFile() !=null) {
    		 String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
    		 for(MultipartFile file:home.getFile())
    		 {
    			 byte[] bytes = file.getBytes();
    			 String filename = UUID.randomUUID() + "." + Objects.requireNonNull(file.getContentType().split("/")[1]);
    			 Files.write(Paths.get(path + File.separator + filename), bytes);
    			 homePicturesRepository.save(new HomePictures(filename,home));
    			 
    		 }
    	}
    	
//    	 SecurityContext securityContext=SecurityContextHolder.getContext();
//         Authentication authentication=securityContext.getAuthentication();
//         String emailUser=null;
//         emailUser= authentication.getName();
//         Long id=userService.findByEmail(emailUser);
//         post.setUser(userService.findById(id));
        if(home.getId() !=null)
        {
            Home homeExist=homeRepository.getHomeById(home.getId());
            MyBeanCopy myBeanCopy=new MyBeanCopy();
            myBeanCopy.copyProperties(homeExist,home);
            return homeRepository.save(homeExist);
        }
        if(home.getCountOfParking() ==null)
            home.setCountOfParking(0L);
        if(home.getCountOfPortions() ==null)
            home.setCountOfPortions(0L);
        if(home.getHouseArea() ==null)
            home.setHouseArea(0L);
        if(home.getNumberOfBathrooms() ==null)
            home.setNumberOfBathrooms(0L);
        if(home.getNumberOfBedrooms() ==null)
            home.setNumberOfBedrooms(0L);
        if(home.getNumberOfToilets() ==null)
            home.setNumberOfToilets(0L);
        if(home.getTotalArea() ==null)
            home.setTotalArea(0L);
        if(home.getYearOfConstruction() ==null)
            home.setYearOfConstruction(0L);


        return homeRepository.save(home);
    }

    public void deleteHome(Home home)
    {
         homeRepository.delete(home);
    }
    public void deleteHomeById(Long id)
    {
         homeRepository.deleteById(id);
    }

    public Page<Home> findBySearch(Home home,Pageable pageable){
        Page<Home> homes= homeRepository.findBySearch(home,(home.getCategory()!=null?(long)home.getCategory().size():0L),pageable);
        return homes;
    }
    public Page<Home> findByUserId(User user, Pageable pageable){
        Page<Home> homes= homeRepository.getHomeByUser(user,pageable);
        return homes;
    }
    public Page<Home> findDisabledHome( Pageable pageable){
        Page<Home> homes= homeRepository.getDisabledHome(pageable);
        return homes;
    }
    
    public Home getHomeById(Long id)
    {
    	return homeRepository.getHomeById(id);
    }

    public List<Home> getAllHome()
    {
        return homeRepository.findAll();
    }

    public  void enableHome(Home home)
    {
        homeRepository.enableHome(home);
    }

    public  void disableHome(Home home)
    {
        homeRepository.disableHome(home);
    }
}
