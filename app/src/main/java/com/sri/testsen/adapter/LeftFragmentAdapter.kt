package com.sri.testsen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.sri.testsen.model.Listings
import com.sri.testsen.R

class LeftFragmentAdapter(private val dataList: List<Listings>, private val context: Context) : RecyclerView.Adapter<LeftFragmentAdapter.ViewHolder>() {

    //Declaring Global variable the value of premium and standard will used to load relevant listing
    private val premium = 1
    private val standard = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //This statement checks and loads relevant view to recycler view
        val v = LayoutInflater.from(parent.context).inflate(if (viewType == premium) R.layout.premium_listing else R.layout.standard_listing, parent, false)
        return ViewHolder(v)
    }
    // This function will load data to appropriate listing
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTV.text = dataList[position].Area
        holder.addressTV.text = "${dataList[position].Location.Address} ${dataList[position].Location.Suburb} ${dataList[position].Location.State}"
        holder.agentnameTV.text = "${dataList[position].owner.name} ${dataList[position].owner.lastName}"
        holder.bedroomsTV.text = dataList[position].Bedrooms.toString()
        holder.bathroomsTV.text = " ${dataList[position].bathrooms} "
        holder.carparkTV.text = " ${dataList[position].Carspaces} "
        holder.progressBar.visibility = View.VISIBLE
        //picasso library is used to load image to imageview
        Picasso.with(context)
                .load(dataList[position].AgencyLogoUrl)
                .into(holder.propertyIV, object : Callback {
                    override fun onSuccess() {
                        // Progress bar is closed as soon as the image is loaded
                        holder.progressBar.visibility = View.GONE
                    }
                    override fun onError() {
                        Picasso.with(context)
                                .load(R.drawable.error_image)
                                .into(holder.agentIV)
                    }
                })
        //picasso  library is used to load image to circular image view
        Picasso.with(context)
                .load(dataList[position].owner.image.big.url)
                .resize(100, 100)
                .into(holder.agentIV)
        holder.relativeLayout.setOnClickListener {
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val rightFragment = fragmentManager.findFragmentById(R.id.right_fragment)
            //Get the TextView object in right Fragment.
            val rightFragmentTextView = rightFragment!!.view!!.findViewById<TextView>(R.id.right_fragment_textview)
            //Set text in right Fragment TextView.
            rightFragmentTextView.text = " Id =" + dataList[holder.adapterPosition].Id + "\n Name = " + dataList[holder.adapterPosition].owner.name + " " + dataList[holder.adapterPosition].owner.lastName
        }
    }
    //this function returns the size of array list with json data
    override fun getItemCount(): Int {
        return dataList.size
    }
    //Function to find if is_premium is true or not!
    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].is_premium == premium) premium else standard
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        //declaring variable
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.listing_layout)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        val titleTV: TextView = itemView.findViewById(R.id.title_TV)
        val addressTV: TextView = itemView.findViewById(R.id.address_tv)
        val agentnameTV: TextView = itemView.findViewById(R.id.agent_name_TV)
        val bedroomsTV: TextView = itemView.findViewById(R.id.bedroom_TV)
        val bathroomsTV: TextView = itemView.findViewById(R.id.bathroom_TV)
        val carparkTV: TextView = itemView.findViewById(R.id.carpark_TV)
        val propertyIV: ImageView = itemView.findViewById(R.id.property_image_view)
        val agentIV: ImageView = itemView.findViewById(R.id.agent_IV)

        override fun onClick(v: View?) {

        }

    }
}