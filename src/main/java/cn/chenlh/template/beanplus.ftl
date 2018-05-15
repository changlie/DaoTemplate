package ${bean._package};

<#if (bean.imports)??>
<#list bean.imports as import>
import ${import};
</#list>
</#if>
/***
 *  @author changlie
 */
@TableName("${tableName}")
public class ${bean.name}{

<#list bean.fields as field>
	<#if field.name=="id">
	@PrimaryKey
	</#if>
	private ${field.type} ${field.name};
</#list>

	public static ${bean.name} instance() {
		return new ${bean.name}();
	}

<#list bean.fields as field>
	public ${bean.name} set${field.NAME}(${field.type} ${field.name}){
		this.${field.name} = ${field.name};
		return this;
	}
	public ${field.type} get${field.NAME}(){
		return this.${field.name};
	}
	
</#list>

}