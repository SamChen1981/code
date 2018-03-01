package com.createcode.template.app;

import com.createcode.common.CodeUtil;

public class AppRequest {
	public static StringBuilder genSB(String pName, String clsUpp, String auth, String cName, String idType, String clsLow) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append("\r\n");
		sb.append("\r\nimport java.util.HashMap;");
		sb.append("\r\nimport java.util.Map;");
		sb.append("\r\n");
		sb.append("\r\nimport org.json.JSONException;");
		sb.append("\r\nimport org.json.JSONObject;");
		sb.append("\r\n");
		sb.append("\r\nimport com.android.volley.AuthFailureError;");
		sb.append("\r\nimport com.android.volley.Response;");
		sb.append("\r\nimport com.android.volley.Response.Listener;");
		sb.append("\r\nimport com.android.volley.VolleyError;");
		sb.append("\r\nimport com.android.volley.toolbox.JsonObjectRequest;");
		sb.append("\r\nimport com.android.volley.toolbox.StringRequest;");
		sb.append("\r\nimport com.google.gson.Gson;");
		sb.append("\r\n");
		sb.append("\r\nimport com.gzz.Constants;");
		sb.append("\r\nimport com.gzz.MyApplication;");
		sb.append("\r\nimport com.gzz.SPUtils;");
		sb.append("\r\nimport com.gzz.ToastUtils;");
		sb.append("\r\n");
		sb.append("\r\n/**");
		sb.append("\r\n * @友情提示: 请清理掉用不到的代码包括这段注释");
		sb.append("\r\n **/");
		sb.append(CodeUtil.classComment(auth, cName + "--网络请求层"));
		sb.append("\r\npublic class " + clsUpp + "Request {");
		sb.append(CodeUtil.methodComment("新增" + cName));
		sb.append("\r\n	public void save(" + clsUpp + " " + clsLow + ", Listener<JSONObject> listener) {");
		sb.append("\r\n		JSONObject json = null;");
		sb.append("\r\n		try {");
		sb.append("\r\n			json = new JSONObject(new Gson().toJson(" + clsLow + "));");
		sb.append("\r\n		} catch (JSONException e) {");
		sb.append("\r\n			e.printStackTrace();");
		sb.append("\r\n		}");
		sb.append("\r\n		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + \"" + clsLow
				+ "/save\", json, listener, new Response.ErrorListener() {");
		sb.append("\r\n			public void onErrorResponse(VolleyError error) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append("\r\n	");
		sb.append(CodeUtil.methodComment("修改" + cName));
		sb.append("\r\n	public void update(" + clsUpp + " " + clsLow + ", Listener<JSONObject> listener) {");
		sb.append("\r\n		JSONObject json = null;");
		sb.append("\r\n		try {");
		sb.append("\r\n			json = new JSONObject(new Gson().toJson(" + clsLow + "));");
		sb.append("\r\n		} catch (JSONException e) {");
		sb.append("\r\n			e.printStackTrace();");
		sb.append("\r\n		}");
		sb.append("\r\n		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + \"" + clsLow
				+ "/update\", json, listener, new Response.ErrorListener() {");
		sb.append("\r\n			public void onErrorResponse(VolleyError error) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(CodeUtil.methodComment("按主键删除" + cName));
		sb.append("\r\n	public void delete(" + idType + " id, Listener<String> listener) {");
		sb.append("\r\n		StringRequest request = new StringRequest(1, Constants.HOST + \"" + clsLow
				+ "/delete?id=\" + id, listener, new Response.ErrorListener() {");
		sb.append("\r\n			@Override");
		sb.append("\r\n			public void onErrorResponse(VolleyError volleyError) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			@Override");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(CodeUtil.methodComment("按条件查询" + cName + "列表"));
		sb.append("\r\n	public void queryList(" + clsUpp + "Cond cond, Listener<JSONObject> listener) {");
		sb.append("\r\n		JSONObject json = null;");
		sb.append("\r\n		try {");
		sb.append("\r\n			json = new JSONObject(new Gson().toJson(cond));");
		sb.append("\r\n		} catch (JSONException e) {");
		sb.append("\r\n			// LogUtil.i(" + clsLow + ".toString());");
		sb.append("\r\n			e.printStackTrace();");
		sb.append("\r\n		}");
		sb.append("\r\n		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + \"" + clsLow
				+ "/queryList\", json, listener, new Response.ErrorListener() {");
		sb.append("\r\n			public void onErrorResponse(VolleyError error) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append("\r\n ");
		sb.append(CodeUtil.methodComment("按条件查询" + cName + "分页列表"));
		sb.append("\r\n	public void queryPage(" + clsUpp + "Cond cond, Listener<JSONObject> listener) {");
		sb.append("\r\n");
		sb.append("\r\n		JSONObject json = null;");
		sb.append("\r\n		try {");
		sb.append("\r\n			json = new JSONObject(new Gson().toJson(cond));");
		sb.append("\r\n		} catch (JSONException e) {");
		sb.append("\r\n			e.printStackTrace();");
		sb.append("\r\n		}");
		sb.append("\r\n		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + \"" + clsLow
				+ "/queryPage\", json, listener, new Response.ErrorListener() {");
		sb.append("\r\n			public void onErrorResponse(VolleyError error) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append("\r\n ");
		sb.append(CodeUtil.methodComment("按主键查询" + cName + "单个数据"));
		sb.append("\r\n	public void findById(" + idType + " id, Listener<String> listener) {");
		sb.append("\r\n		StringRequest request = new StringRequest(1, Constants.HOST + \"" + clsLow
				+ "/findById?id=\" + id, listener, new Response.ErrorListener() {");
		sb.append("\r\n			@Override");
		sb.append("\r\n			public void onErrorResponse(VolleyError volleyError) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			@Override");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append("\r\n ");
		sb.append(CodeUtil.methodComment("按条件查询" + cName + "数据个数"));
		sb.append("\r\n	public void queryCount(" + clsUpp + "Cond cond, Listener<JSONObject> listener) {");
		sb.append("\r\n		JSONObject json = null;");
		sb.append("\r\n		try {");
		sb.append("\r\n			json = new JSONObject(new Gson().toJson(cond));");
		sb.append("\r\n		} catch (JSONException e) {");
		sb.append("\r\n			// LogUtil.i(" + clsLow + ".toString());");
		sb.append("\r\n			e.printStackTrace();");
		sb.append("\r\n		}");
		sb.append("\r\n		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + \"" + clsLow
				+ "/queryCount\", json, listener, new Response.ErrorListener() {");
		sb.append("\r\n			public void onErrorResponse(VolleyError error) {");
		sb.append("\r\n				ToastUtils.showToastShort(\"请检查手机网络！\");");
		sb.append("\r\n			}");
		sb.append("\r\n		}) {");
		sb.append("\r\n			public Map<String, String> getHeaders() throws AuthFailureError {");
		sb.append("\r\n				HashMap<String, String> map = new HashMap<String, String>();");
		sb.append("\r\n				map.put(\"Authorization\", SPUtils.getString(Constants.MY_TOKEN, \"\"));");
		sb.append("\r\n				return map;");
		sb.append("\r\n			}");
		sb.append("\r\n		};");
		sb.append("\r\n		MyApplication.getHttpQueues().add(request);");
		sb.append("\r\n	}");
		sb.append("\r\n}");

		return sb;
	}
}
