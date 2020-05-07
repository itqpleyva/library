package mainPackage.Websockets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.MultiValueMap;

import mainPackage.Model.Category;
import mainPackage.bookRepository.categoryRepository;

public class RmeSessionChannelInterceptor implements ChannelInterceptor {
	
	@Autowired
	categoryRepository categoryrepo;

@Override
public Message<?> preSend(Message<?> message, MessageChannel channel) {
	
MessageHeaders headers = message.getHeaders();

StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

if (accessor.getFirstNativeHeader("category")!= null && accessor.getCommand().toString().equals("DISCONNECT")) {

	Optional<Category> cat = categoryrepo.findById(accessor.getFirstNativeHeader("category").toString());
	
	int cant = cat.get().getCantidad();
	
	cat.get().setCantidad(cant-1);		
	categoryrepo.save(cat.get());
}
if(accessor.getCommand().toString().equals("SUBSCRIBE")){
	
	MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS,MultiValueMap.class);

	if (multiValueMap != null) {
		
		for (Map.Entry<String, List<String>> head : multiValueMap.entrySet()) {
			if(head.getKey().equals("category")) {
						
				Optional<Category> cat = categoryrepo.findById(head.getValue().get(0));
				
				int cant = cat.get().getCantidad();
				
				cat.get().setCantidad(cant+1);		
				
				categoryrepo.save(cat.get());				
			}			
		}			
	}
}

    return message;
}

}