package ${bean._package};

<#if (bean.imports)??>
<#list bean.imports as import>
import ${import};
</#list>
</#if>
/***
 *  @author changlie
 */
public class ${bean.name}{

<#list bean.fields as field>
	private ${field.type} ${field.name};
</#list>

<#list bean.fields as field>
	public void set${field.NAME}(${field.type} ${field.name}){
		this.${field.name} = ${field.name};
	}
	public ${field.type} get${field.NAME}(){
		return this.${field.name};
	}
	
</#list>

}