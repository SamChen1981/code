package com.gzz.createcode.template.vuex;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class Js {
	public static StringBuilder create(List<Field> fList, String cName, String auth, String lowUpp) {
		String idName = fList.get(0).getName().toLowerCase();
		StringBuilder sb = new StringBuilder();
		StringBuilder pageColum = new StringBuilder();
		StringBuilder cond = new StringBuilder();
		StringBuilder initform = new StringBuilder();
		StringBuilder validate = new StringBuilder();

		for (Field field : fList) {
			String name = field.getName();
			String comments = field.getComment();
			initform.append("\r\n          " + name + ": null,");
			cond.append("\r\n      <FormItem label=\"" + comments + "\"><Input placeholder=\"请输入" + comments + "\" size=\"small\" v-model=\"form." + name + "\"></Input></FormItem>");
			pageColum.append("\r\n          { title: '" + comments + "', key: '" + name + "'},");
			validate.append("\r\n          " + name + ": [");
			validate.append("\r\n            {required: true, message: '请输入" + comments + "', trigger: 'blur'},");
			validate.append("\r\n            {min: 1, max: 10, message: '" + comments + "长度不正确', trigger: 'blur'},");
			validate.append("\r\n          ],");
		}
		sb.append(Utils.pageNote(cName + "业务逻辑", auth));
		sb.append("\r\nimport http from '../../../utils/http';");
		sb.append("\r\nimport {Message, Modal} from 'iview';");
		sb.append("\r\n//初始数据");
		sb.append("\r\nconst initForm = {");
		sb.append(initform);
		sb.append("\r\n};");
		sb.append("\r\n//模型");
		sb.append("\r\nexport default {");
		sb.append("\r\n  state: {");
		sb.append("\r\n    //分页列表");
		sb.append("\r\n    total: 0,");
		sb.append("\r\n    page: 1,");
		sb.append("\r\n    dataList: [],");
		sb.append("\r\n    loading: false,");
		sb.append("\r\n    searchForm: {");
		sb.append(initform);
		sb.append("\r\n    },");
		sb.append("\r\n    //新增与修改");
		sb.append("\r\n    form: {...initForm},");
		sb.append("\r\n    rules: {");
		sb.append(validate);
		sb.append("\r\n    },");
		sb.append("\r\n    title: '',");
		sb.append("\r\n    dialogMode: \"save\",");
		sb.append("\r\n    show: false,");
		sb.append("\r\n  },");
		sb.append("\r\n  getters: {},");
		sb.append("\r\n  mutations: {");
		sb.append("\r\n    ['" + lowUpp + "/refresh'](state){");
		sb.append("\r\n      state.loading = true;");
		sb.append("\r\n      const requestData = {...state.searchForm, page: state.page - 1};");
		sb.append("\r\n      http.post(\"/api/" + lowUpp + "/queryPage\", JSON.stringify(requestData)).then(res => {");
		sb.append("\r\n        state.loading = false;");
		sb.append("\r\n        state.dataList = res.data.content;");
		sb.append("\r\n        state.total = res.data.totalElements;");
		sb.append("\r\n      }).catch(res => {");
		sb.append("\r\n        Message.error({");
		sb.append("\r\n          content: \"获取" + cName + "列表失败：\" + res");
		sb.append("\r\n        });");
		sb.append("\r\n        state.loading = false;");
		sb.append("\r\n      });");
		sb.append("\r\n    },");
		sb.append("\r\n    [\"" + lowUpp + "/addDialog\"](state){");
		sb.append("\r\n      state.title = \"新增" + cName + "\";");
		sb.append("\r\n      state.dialogMode = \"save\";");
		sb.append("\r\n      state.form = {...initForm};");
		sb.append("\r\n      state.show = true;");
		sb.append("\r\n    },");
		sb.append("\r\n    [\"" + lowUpp + "/editDialog\"](state, row){");
		sb.append("\r\n      state.title = \"修改" + cName + "\";");
		sb.append("\r\n      state.dialogMode = \"update\";");
		sb.append("\r\n      state.form = {...row};");
		sb.append("\r\n      state.show = true;");
		sb.append("\r\n    },");
		sb.append("\r\n    [\"" + lowUpp + "/showDialog\"](state, show){");
		sb.append("\r\n      state.show = show;");
		sb.append("\r\n    },");
		sb.append("\r\n    [\"" + lowUpp + "/setPage\"](state, page){");
		sb.append("\r\n      state.page = page;");
		sb.append("\r\n    },");
		sb.append("\r\n  },");
		sb.append("\r\n  actions: {");
		sb.append("\r\n    [\"" + lowUpp + "/deleteAction\"]({state, dispatch, commit}, row){");
		sb.append("\r\n      Modal.confirm({");
		sb.append("\r\n        title: '提示',");
		sb.append("\r\n        content: '您确定要删除吗?',");
		sb.append("\r\n        closable:true,");
		sb.append("\r\n        onOk: () => {");
		sb.append("\r\n          http.delete(\"/api/" + lowUpp + "/delete\", {");
		sb.append("\r\n            params: {ids: [row." + idName + "]}");
		sb.append("\r\n          }).then(res => {");
		sb.append("\r\n            Message.success({");
		sb.append("\r\n              content: \"删除成功\"");
		sb.append("\r\n            });");
		sb.append("\r\n            commit(\"" + lowUpp + "/refresh\");");
		sb.append("\r\n          }).catch(res => {");
		sb.append("\r\n            Message.error({");
		sb.append("\r\n              content: \"删除失败：\" + res");
		sb.append("\r\n            });");
		sb.append("\r\n          });");
		sb.append("\r\n        },");
		sb.append("\r\n        onCancel: () => {");
		sb.append("\r\n");
		sb.append("\r\n        }");
		sb.append("\r\n      });");
		sb.append("\r\n    },");
		sb.append("\r\n    ['" + lowUpp + "/save']({state, dispatch, commit}){");
		sb.append("\r\n      http.post(\"/api/" + lowUpp + "/\" + state.dialogMode, JSON.stringify(state.form)).then(res => {");
		sb.append("\r\n        commit(\"" + lowUpp + "/refresh\");");
		sb.append("\r\n        commit(\"" + lowUpp + "/showDialog\", false);");
		sb.append("\r\n      }).catch(res => {");
		sb.append("\r\n        Message.error({");
		sb.append("\r\n          content: '保存" + cName + "信息失败' + res");
		sb.append("\r\n        });");
		sb.append("\r\n      })");
		sb.append("\r\n    },");
		sb.append("\r\n  }");
		sb.append("\r\n};");
		return sb;
	}
}
