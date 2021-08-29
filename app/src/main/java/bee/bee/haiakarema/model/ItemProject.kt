package bee.bee.haiakarema.model

import java.io.Serializable

data class ItemProject(

    val id:String,
    val name:String,
    val intro:String,
    val pdf:List<String>,
    val videos:List<String>,
):Serializable