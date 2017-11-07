package edu.orangecoastcollege.cs273.petprotector;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to provide custom adapter for the <code>College</code> list.
 */
public class PetListAdaptor extends ArrayAdapter<Pet> {

    private Context mContext;
    private List<Pet> mPetList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>CollegeListAdapter</code> given a mContext, resource id and list of colleges.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param petList The list of colleges to display
     */
    public PetListAdaptor(Context c, int rId, List<Pet> petList) {
        super(c, rId, petList);
        mContext = c;
        mResourceId = rId;
        mPetList = petList;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the College selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(mResourceId, null);

        ImageView listItemImageView = (ImageView) listItemView.findViewById(R.id.listItemImageView);
        TextView listItemNameTextView = (TextView) listItemView.findViewById(R.id.listItemNameTextView);
        TextView listItemDetailTextView = (TextView) listItemView.findViewById(R.id.listItemDetailTextView);

        Pet selectedPet = mPetList.get(pos);

        listItemImageView.setImageURI(Uri.parse(selectedPet.getImageURI()));
        listItemNameTextView.setText(selectedPet.getName());
        listItemDetailTextView.setText(selectedPet.getDetails());

        return listItemView;
    }



//    @Nullable
//    @Override
//    public Pet getItem(int position) {
//        return mPetList.get(position);
//    }
}
