from django.contrib import admin

from .models import GlassesModel, GlassesVariant, ExtraOption, GlassesForm, GlassesFrame

# Register your models here.
admin.site.register(GlassesModel)
admin.site.register(GlassesVariant)
admin.site.register(ExtraOption)
admin.site.register(GlassesFrame)
admin.site.register(GlassesForm)