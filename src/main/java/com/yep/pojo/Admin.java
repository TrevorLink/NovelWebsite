package com.yep.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * @author HuangSir
 * @date 2022-02-21 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements UserDetails {
   private Integer id;
   private String username;
   private  String password;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
