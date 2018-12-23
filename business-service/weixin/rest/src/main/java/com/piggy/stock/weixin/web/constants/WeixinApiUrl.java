package com.piggy.stock.weixin.web.constants;

public interface WeixinApiUrl {
	/**
	 * 获取平台访问令牌
	 */
	String API_COMPONENT_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

	/**
	 * 获取预授权码
	 */
	String API_CREATE_PREAUTHCODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=${componentAccessToken}";

	/**
	 * 获取公众号授权码
	 */
	String API_QUERY_AUTH = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=${componentAccessToken}";

	/**
	 * 刷新公众号令牌
	 */
	String API_AUTHORIZER_TOKEN = "https:// api.weixin.qq.com /cgi-bin/component/api_authorizer_token?component_access_token=${componentAccessToken}";

	/**
	 * 获取公众号信息
	 */
	String API_GET_AUTHORIZER_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=${componentAccessToken}";

	/**
	 * 获取公众号的选项设置信息
	 */
	String API_GET_AUTHORIZER_OPTION = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option?component_access_token=${componentAccessToken}";

	/**
	 * 设置公众号的选项信息
	 */
	String API_SET_AUTHORIZER_OPTION = "https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option?component_access_token=${componentAccessToken}";

	/**
	 * 获取公众号用户列表。（只取总数，即关注数）
	 */
	String API_GET_USER = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

	/**
	 * 获取公众号tag
	 */
	String API_GET_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";

}
