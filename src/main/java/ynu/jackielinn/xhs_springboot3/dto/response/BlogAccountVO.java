package ynu.jackielinn.xhs_springboot3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class   BlogAccountVO {
     private Long id;
     private String username;
     private String avatar;
}
