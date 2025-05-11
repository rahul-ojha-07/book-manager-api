package in.rahulojha.bookmanagerapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {
    private static final Map<String, RateLimitInfo> requestCountPerIPAddress =
        new ConcurrentHashMap<>();

    private static final long LIMIT_WINDOW_MS = 60_000;
    private static final long MAX_REQUESTS = 5;

    @Data
    private static class RateLimitInfo {
        private int requestCount;
        private long windowStrat;

        RateLimitInfo() {
            this.requestCount = 0;
            this.windowStrat = Instant.now().toEpochMilli();
        }
        public void incrementRequestCount() {
            this.requestCount ++;
        }
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientIpAddress = request.getRemoteAddr();
        long currentTime = Instant.now().toEpochMilli();
        RateLimitInfo info = requestCountPerIPAddress.computeIfAbsent(clientIpAddress, k -> new RateLimitInfo());

        synchronized (info) {
            if (currentTime - info.getWindowStrat() > LIMIT_WINDOW_MS) {
                info.setWindowStrat(currentTime);
                info.setRequestCount(1);
            } else {
                info.incrementRequestCount();
            }

            if (info.getRequestCount() > MAX_REQUESTS) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setHeader("X-RateLimit-Remaining", MAX_REQUESTS - info.getRequestCount() + "");
                response.getWriter().write("Rate limit exceeded. Try again later.");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
