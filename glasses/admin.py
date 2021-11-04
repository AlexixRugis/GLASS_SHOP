from django.contrib import admin

from .models import GlassesModel, ExtraOption, GlassesForm, GlassesFrame

# Register your models here.
admin.site.register(GlassesModel)
admin.site.register(ExtraOption)
admin.site.register(GlassesFrame)
admin.site.register(GlassesForm)