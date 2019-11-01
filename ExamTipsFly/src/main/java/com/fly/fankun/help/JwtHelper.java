package com.fly.fankun.help;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fly.fankun.globals.CacheConsts;
import com.fly.fankun.model.vo.TokenUserVo;
import com.fly.fankun.util.UUIDUtil;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtHelper {
    /**
     * 缓存管理器
     */
    @Autowired
    private RedisHelp cacheHelper;
    /**
     * JWT密钥
     */
    private static final String JWT_SECRET_KEY = "53e1f568a46e5ab1f442cf8e0932aef34457139b";

    public String createEnterpriseToken(Integer id, Integer authType) {
        String jwtId = UUIDUtil.getUUID();
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
        String token = JWT.create().withClaim("id", id).withClaim("authType", authType).withClaim("jwt-id", jwtId).
            withExpiresAt(new Date(System.currentTimeMillis() + CacheConsts.JWT_TOKEN_EXPIRE_TIME)).sign(algorithm);
        //JWT过期时间一致
        cacheHelper.set("JWT-SESSION-" + jwtId, token, CacheConsts.JWT_TOKEN_EXPIRE_TIME);
        return token;
    }

    public boolean verifyEnterpriseToken(String token) {
        try {
            String key = "JWT-SESSION-" + getJwtIdByToken(token);
            String redisToken = (String) cacheHelper.get(key);
            log.info("redis key:" + key + ",redisToken:" + redisToken);
            if (!StringUtils.equals(redisToken, token)) {
                return false;
            }
            //4 . 得到算法相同的JWTVerifier
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            //3 . 获取校验只
            Integer id = getIdByToken(redisToken);
            Integer authType = getAuthTypeByToken(redisToken);
            // 如果需要校验的值有任何一个为空，就认为token失效,需要重新登陆
            if (null == id  || null == authType) {
                return false;
            }
            JWTVerifier verifier = JWT.require(algorithm).withClaim("id", id).withClaim("authType", authType).withClaim("jwt-id", getJwtIdByToken(redisToken)).
                //JWT 正确的配置续期姿势
                    acceptExpiresAt(System.currentTimeMillis() + CacheConsts.JWT_TOKEN_EXPIRE_TIME).build();
            //5 . 验证token
            verifier.verify(redisToken);
            //6 . Redis缓存JWT续期
            cacheHelper.set("JWT-SESSION-" + getJwtIdByToken(token), redisToken, CacheConsts.JWT_TOKEN_EXPIRE_TIME);
            return true;
        } catch (Exception e) { //捕捉到任何异常都视为校验失败
            log.error("Error: OutwebJwtHelper.verifyEnterpriseToken!", e);
            return false;
        }
    }

    /**
     * 根据Token 获取jwt-id
     */
    public String getJwtIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("jwt-id").asString();
    }

    /**
     * 根据Token获取id
     */
    public Integer getIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("id").asInt();
    }

    /**
     * 根据Token获取authType
     */
    public Integer getAuthTypeByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("authType").asInt();
    }

    /**
     * 获取TOken用户信息
     * @param token
     * @return
     * @throws JWTDecodeException
     */
    public TokenUserVo getUserInfoByToken(String token) throws JWTDecodeException {
        Integer id = getIdByToken(token);
        Integer authTypeByToken = getAuthTypeByToken(token);
        return new TokenUserVo(id, authTypeByToken);
    }
}
