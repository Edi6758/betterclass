package com.empresa.suporte.controller;

import com.empresa.suporte.config.SecurityWebConfig;
import com.empresa.suporte.img.FileUploadUtil;
import com.empresa.suporte.model.Usuario;
import com.empresa.suporte.repository.SalaRepository;
import com.empresa.suporte.repository.PermissaoRepository;
import com.empresa.suporte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class UsuarioController {

	public boolean erroLogin = false;
    public boolean erroCpf = false;
    public boolean erroEmail = false;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @Autowired
    private SalaRepository horarioRepository;

    @GetMapping("/usuario/list")
    public String listUsuario(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll(Sort.by("nome")));
        return "usuario/list";
    }

    @GetMapping("/usuario/add")
    public String addUsuario(Model model) {
        
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira"));
        model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira"));
        model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira"));
        model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira"));
        model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira"));
        model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado"));

        
        if(erroLogin == true || erroCpf == true || erroEmail == true) {
        	
        	
        	if(erroCpf == true) model.addAttribute("erroCpf", "true");
        	if(erroEmail == true) model.addAttribute("erroEmail", "true");
        	if(erroLogin == true) model.addAttribute("erroLogin", "true");
        	
        	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n TESTE LOGIN 02 (USUARIO/ADD) "+erroLogin + erroEmail + erroLogin);
        	erroLogin = false;
        	erroCpf = false;
        	erroEmail = false;
        }
        
        return ("usuario/add");
    }

    
    @PostMapping("/usuario/save")
    public RedirectView saveUsuario(Usuario usuario, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String url = "";        

        try {
            if (usuario != null) {
                boolean erro = false;
                
                
                System.out.println(usuario.getId());
                SecurityWebConfig.geraSenha(usuario);
               if(usuario.getId() == null) {
            	   
                   url = "/usuario/add";
                   model.addAttribute("usuario", usuario);
                   model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira"));
                   model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira"));
                   model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira"));
                   model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira"));
                   model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira"));
                   model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado"));
                   
                   if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
                	   erroLogin = true;
                   } 
                   
                   if (usuarioRepository.findByCpf(usuario.getCpf()) != null) {
                       erroCpf = true;
                   } 
                   
                   if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
                       erroEmail = true;
                   }
                   
                   if(erroLogin == true || erroCpf == true || erroEmail == true) {
                	   System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n TESTE LOGIN 01 (USUARIO/SAVE) "+erroLogin + erroCpf + erroEmail);
                	   return new RedirectView("/usuario/add");
                   }
                   

               }else{
                   url = "/usuario/edit";
                   model.addAttribute("usuario", usuario);
                   model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira"));
                   model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira"));
                   model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira"));
                   model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira"));
                   model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira"));
                   model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado"));

                   System.out.println("\n\n\n\n\n\n print: " + usuarioRepository.findByLoginAndIdNot(usuario.getLogin(), usuario.getId()));

                   if (usuarioRepository.findByLoginAndIdNot(usuario.getLogin(), usuario.getId()) != null) {
                       erroLogin= true;
                   }
                   
                   if (usuarioRepository.findByEmailAndIdNot(usuario.getEmail(), usuario.getId()) != null) {
                       erroEmail = true;
                   }
                   
                   if(erroLogin == true ||  erroEmail == true) {
                	   return new RedirectView("/usuario/edit/"+usuario.getId());
                   }
                   
               }
               if(!erro){
                   String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                   System.out.println("\n\n\n\n\n\n\n\n\n\n FILENAME: "+fileName);
                   usuario.setFoto(fileName);

                   Usuario savedUser = usuarioRepository.save(usuario);
                   String uploadDir = "usuario-foto/" + savedUser.getId();

                   FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
                   usuarioRepository.save(usuario);
                   url = "redirect:/usuario/view/" + usuario.getId() + "/" + true;
               }
            }

        }catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
               // url = "redirect:/usuario/view/" + usuario.getId() + "/" + true;
        }

        System.out.println("\n\n\n url" + url);
        return new RedirectView("/usuario/list", true);

    }

    @GetMapping("/usuario/view/{id}/{salvo}")
    public String viewUsuario(@PathVariable long id, @PathVariable boolean salvo, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id));
        model.addAttribute("salvo", salvo);
        return "usuario/view_modal";
    }

    //----------VIEW USER----------
    @GetMapping("/usuario/viewUser")
    public String viewUser(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        //long idUser =  usuarioRespository.findByLogin(login).getId();
        model.addAttribute("usuario", usuarioRepository.findByLogin(login));
        return "usuario/viewUser";

    }

    @GetMapping("/usuario/edit/{id}")
    public String editUsuario(@PathVariable long id, Model model) {

        model.addAttribute("horarioSegunda", horarioRepository.findBySemanaAndLimite("Segunda-feira", id));
        model.addAttribute("horarioTerca", horarioRepository.findBySemanaAndLimite("Terça-feira", id));
        model.addAttribute("horarioQuarta", horarioRepository.findBySemanaAndLimite("Quarta-feira", id));
        model.addAttribute("horarioQuinta", horarioRepository.findBySemanaAndLimite("Quinta-feira", id));
        model.addAttribute("horarioSexta", horarioRepository.findBySemanaAndLimite("Sexta-feira", id));
        model.addAttribute("horarioSabado", horarioRepository.findBySemanaAndLimite("Sábado", id));
        
        if(erroLogin == true || erroEmail == true) {
        	
        	if(erroLogin == true) model.addAttribute("erroLogin", "true");
        	if(erroEmail == true) model.addAttribute("erroEmail", "true");
        	
        	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n TESTE LOGIN 02 (USUARIO/EDIT) "+erroLogin + erroEmail);
        	erroLogin = false;
        	erroEmail = false;
        }
        
        model.addAttribute("usuario", usuarioRepository.findById(id));
        return "usuario/edit";

    }

    @GetMapping("/usuario/delete/{id}")
    public String deleteUsuario(@PathVariable long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return "redirect:/usuario/list";
    }

}
