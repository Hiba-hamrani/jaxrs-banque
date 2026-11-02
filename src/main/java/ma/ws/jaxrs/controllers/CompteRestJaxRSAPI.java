package ma.ws.jaxrs.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.ws.jaxrs.entities.Compte;
import ma.ws.jaxrs.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository;

    // READ : tous les comptes
    @Path("/comptes")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    // READ : un compte par son id
    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte getCompte(@PathParam("id") Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    // CREATE
    @Path("/comptes")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    // UPDATE
    @Path("/comptes/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte updateCompte(@PathParam("id") Long id, Compte compte) {
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null) {
            existingCompte.setSolde(compte.getSolde());
            existingCompte.setDateCreation(compte.getDateCreation());
            existingCompte.setType(compte.getType());
            return compteRepository.save(existingCompte);
        }
        return null;
    }

    // DELETE
    @Path("/comptes/{id}")
    @DELETE
    public void deleteCompte(@PathParam("id") Long id) {
        compteRepository.deleteById(id);
    }
}