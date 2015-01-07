package com.utn.adapter;

import com.utn.seguidor.R;
import com.utn.vo.MateriaVO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AnioAdapter extends ArrayAdapter<MateriaVO> 
{
	private final int newsItemLayoutResource;
	 
	public AnioAdapter(final Context context, final int newsItemLayoutResource) {
		super(context, 0);
		this.newsItemLayoutResource = newsItemLayoutResource;
	}
 
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) 
	{
		// We need to get the best view (re-used if possible) and then
		// retrieve its corresponding ViewHolder, which optimizes lookup efficiency
		final View view = getWorkingView(convertView);
		final ViewHolder viewHolder = getViewHolder(view);
		final MateriaVO entry = getItem(position);
		
		
		// Setting the title view is straightforward
		viewHolder.titleView.setText(entry.getNombre());
		
		// Setting the subTitle view requires a tiny bit of formatting
		if(entry.getNombre().contains("AÑO"))
		{
			viewHolder.subTitleView.setVisibility(TextView.INVISIBLE);
		}else{
			final String formattedSubTitle = String.format("Estado: %s", entry.getEstado());
			viewHolder.subTitleView.setText(formattedSubTitle);
		}
		
		return view;
	}
 
	private View getWorkingView(final View convertView) {
		// The workingView is basically just the convertView re-used if possible
		// or inflated new if not possible
		View workingView = null;
		
		if(null == convertView) {
			final Context context = getContext();
			final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			workingView = inflater.inflate(newsItemLayoutResource, null);
		} else {
			workingView = convertView;
		}
		
		return workingView;
	}
	
	private ViewHolder getViewHolder(final View workingView) {
		// The viewHolder allows us to avoid re-looking up view references
		// Since views are recycled, these references will never change
		final Object tag = workingView.getTag();
		ViewHolder viewHolder = null;
		
		
		if(null == tag || !(tag instanceof ViewHolder)) {
			viewHolder = new ViewHolder();
			
			viewHolder.titleView = (TextView) workingView.findViewById(R.id.nombre_materia);
			viewHolder.subTitleView = (TextView) workingView.findViewById(R.id.estado_materia);
			
			workingView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) tag;
		}
		
		return viewHolder;
	}
	
	/**
	 * ViewHolder allows us to avoid re-looking up view references
	 * Since views are recycled, these references will never change
	 */
	private static class ViewHolder {
		public TextView titleView;
		public TextView subTitleView;
	}
}
