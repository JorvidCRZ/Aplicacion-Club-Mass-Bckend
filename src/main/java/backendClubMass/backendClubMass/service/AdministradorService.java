package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.AdminRequest;
import backendClubMass.backendClubMass.dto.response.AdminResponse;
import java.util.List;

public interface AdministradorService {
    List<AdminResponse> getAllAdmins();
    AdminResponse getAdminById(Integer id);
    AdminResponse createAdmin(AdminRequest request);
    AdminResponse updateAdmin(Integer id, AdminRequest request);
    boolean deleteAdmin(Integer id);
}